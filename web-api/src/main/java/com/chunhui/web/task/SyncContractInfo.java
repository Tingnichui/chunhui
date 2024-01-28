package com.chunhui.web.task;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.constants.jljs.JljsContractOperateTypeEnum;
import com.chunhui.web.constants.jljs.JljsContractStatusEnum;
import com.chunhui.web.constants.jljs.JljsCourseTypeEnum;
import com.chunhui.web.constants.jljs.JljsOperateStatusEnum;
import com.chunhui.web.dao.JljsClassRecordDao;
import com.chunhui.web.dao.JljsContractInfoDao;
import com.chunhui.web.dao.JljsContractOperateRecordDao;
import com.chunhui.web.pojo.po.BaseDO;
import com.chunhui.web.pojo.po.JljsClassRecord;
import com.chunhui.web.pojo.po.JljsContractInfo;
import com.chunhui.web.pojo.po.JljsContractOperateRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class SyncContractInfo {

    @Resource
    private JljsContractInfoDao jljsContractInfoDao;
    @Resource
    private JljsContractOperateRecordDao jljsContractOperateRecordDao;
    @Resource
    private JljsClassRecordDao jljsClassRecordDao;


    @Scheduled(cron = "0 0 0/1 * * ?")
    public void syncAll() {
        for (JljsContractInfo contractInfo : jljsContractInfoDao.list(Wrappers.lambdaQuery(JljsContractInfo.class).select(BaseDO::getId))) {
            this.sync(contractInfo.getId());
        }
    }

    public void sync(String contractId) {
        Date today = new Date();

        JljsContractInfo contractInfo = jljsContractInfoDao.getById(contractId);
        if (null == contractInfo) {
            log.info("合同不存在，合同id：{}", contractId);
            return;
        }

        // 获取开卡记录 1.没有开卡记录设置为待开卡，重置开始时间、结束时间、总暂停天数 2.有开卡记录设置使用时间
        JljsContractOperateRecord kaikaRecord = jljsContractOperateRecordDao.getOne(
                getBaseSelect(contractId).eq(JljsContractOperateRecord::getContractOperateType, JljsContractOperateTypeEnum.kaika.getCode())
        );
        if (null == kaikaRecord) {
            log.info("合同没有开卡记录，合同id：{}", contractId);
            jljsContractInfoDao.update(
                    Wrappers.lambdaUpdate(JljsContractInfo.class)
                            .eq(BaseDO::getId, contractId)
                            .set(JljsContractInfo::getUseBeginDate, null)
                            .set(JljsContractInfo::getUseEndDate, null)
                            .set(JljsContractInfo::getCourseUseQuantity, null)
                            .set(JljsContractInfo::getCourseRemainQuantity, null)
                            .set(JljsContractInfo::getCourseTotalStopDays, null)
                            .set(JljsContractInfo::getContractStatus, JljsContractStatusEnum.daikaika.getCode())
            );
            return;
        }
        contractInfo.setUseBeginDate(kaikaRecord.getOperateBeginDate());
        contractInfo.setContractStatus(JljsContractStatusEnum.shiyong.getCode());

        // 获取请假记录 计算总暂停天数 重新计算有效期使用天厨
        List<JljsContractOperateRecord> zantingList = jljsContractOperateRecordDao.list(
                getBaseSelect(contractId).eq(JljsContractOperateRecord::getContractOperateType, JljsContractOperateTypeEnum.zanting.getCode())
        );
        int totalStopDays = zantingList.stream().mapToInt(JljsContractOperateRecord::getIntervalDays).sum();
        contractInfo.setCourseTotalStopDays(totalStopDays);
        // 设置结束时间为开始时间之后的 总有效期+暂停天数
        contractInfo.setUseEndDate(DateUtil.offsetDay(contractInfo.getUseBeginDate(), totalStopDays + contractInfo.getCourseUsePeriodDays()));
        // 暂停操作记录不为空，遍历判断是否在暂停中
        if (!zantingList.isEmpty()) {
            // 默认设置为使用中
            contractInfo.setContractStatus(JljsContractStatusEnum.shiyong.getCode());
            // 如果在请假时间内 则设置为暂停
            for (JljsContractOperateRecord record : zantingList) {
                if (DateUtil.isIn(today, record.getOperateBeginDate(), record.getOperateEndDate())) {
                    contractInfo.setContractStatus(JljsContractStatusEnum.zanting.getCode());
                    break;
                }
            }
        }

        // 更新 已使用量、剩余数量
        if (JljsCourseTypeEnum.ci.getCode().equals(contractInfo.getCourseType())) {
            int count = (int) jljsClassRecordDao.count(
                    Wrappers.lambdaQuery(JljsClassRecord.class)
                            .eq(JljsClassRecord::getMemberId, contractInfo.getMemberId())
            );
            contractInfo.setCourseUseQuantity(count);
            contractInfo.setCourseRemainQuantity(contractInfo.getCourseAvailableQuantity() - count);
        }
        if (JljsCourseTypeEnum.tian.getCode().equals(contractInfo.getCourseType())) {
            int courseUsePeriodDays = (int) DateUtil.betweenDay(contractInfo.getUseBeginDate(), today, true);
            contractInfo.setCourseUseQuantity(Math.min(contractInfo.getCourseAvailableQuantity(), courseUsePeriodDays));
            contractInfo.setCourseRemainQuantity(Math.max(contractInfo.getCourseAvailableQuantity() - courseUsePeriodDays, 0));
        }


        // 获取补缴记录 计算实际收取金额
        List<JljsContractOperateRecord> bujiaoList = jljsContractOperateRecordDao.list(
                getBaseSelect(contractId).eq(JljsContractOperateRecord::getContractOperateType, JljsContractOperateTypeEnum.bujiao.getCode())
        );
        if (CollectionUtils.isNotEmpty(bujiaoList)) {

        }

        // 使用期限已经过了
        if (contractInfo.getUseEndDate().compareTo(today) < 0) {
            // 如果是使用中 更新为已完成
            if (JljsContractStatusEnum.shiyong.getCode().equals(contractInfo.getContractStatus())) {
                contractInfo.setContractStatus(JljsContractStatusEnum.wancheng.getCode());
            }
        }

        // 合同是否终止
        JljsContractOperateRecord tuikeRecord = jljsContractOperateRecordDao.getOne(
                getBaseSelect(contractId).eq(JljsContractOperateRecord::getContractOperateType, JljsContractOperateTypeEnum.tuike.getCode())
        );
        if (null != tuikeRecord) {
            contractInfo.setUseBeginDate(tuikeRecord.getOperateBeginDate());
            contractInfo.setContractStatus(JljsContractStatusEnum.zhongzhi.getCode());
        }

        // 更新合同信息
        jljsContractInfoDao.updateById(contractInfo);

    }

    private LambdaQueryWrapper<JljsContractOperateRecord> getBaseSelect(String contractId) {
        LambdaQueryWrapper<JljsContractOperateRecord> queryWrapper = Wrappers.lambdaQuery(JljsContractOperateRecord.class);
        queryWrapper.eq(JljsContractOperateRecord::getContractInfoId, contractId);
        queryWrapper.eq(JljsContractOperateRecord::getOperateStatus, JljsOperateStatusEnum.chenggong.getCode());
        return queryWrapper;
    }

}
