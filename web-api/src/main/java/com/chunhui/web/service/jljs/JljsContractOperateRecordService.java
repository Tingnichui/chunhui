package com.chunhui.web.service.jljs;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.constants.jljs.JljsContractOperateTypeEnum;
import com.chunhui.web.constants.jljs.JljsContractStatusEnum;
import com.chunhui.web.constants.jljs.JljsOperateStatusEnum;
import com.chunhui.web.dao.JljsContractInfoDao;
import com.chunhui.web.dao.JljsContractOperateRecordDao;
import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.BaseDO;
import com.chunhui.web.pojo.po.JljsContractInfo;
import com.chunhui.web.pojo.po.JljsContractOperateRecord;
import com.chunhui.web.pojo.query.JljsContractOperateRecordQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.task.SyncContractInfo;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class JljsContractOperateRecordService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsContractOperateRecordDao jljsContractOperateRecordDao;
    @Resource
    private JljsContractInfoDao jljsContractInfoDao;
    @Resource
    private JljsContractInfoService jljsContractInfoService;
    @Resource
    private SyncContractInfo syncContractInfo;

    public Result<PageResult<JljsContractOperateRecordOutVO>> pageList(JljsContractOperateRecordQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsContractOperateRecordDao.pageListByQurey(query), commonConvert::toJljsContractOperateRecordOutList));
    }

    public Result<JljsContractOperateRecordOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsContractOperateRecordListOut(jljsContractOperateRecordDao.getById(id)));
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(JljsContractOperateRecordSaveVO saveVO) throws Exception {
        JljsContractOperateRecord operateRecord = commonConvert.toJljsContractOperateRecord(saveVO);
        JljsContractInfo contractInfo = jljsContractInfoDao.getById(operateRecord.getContractInfoId());
        if (null == contractInfo) {
            throw new BusinessException("合同不存在");
        }

        try {
            String contractStatus = contractInfo.getContractStatus();
            String operateType = operateRecord.getContractOperateType();
            // 开卡 更新合同开始日期
            if (JljsContractOperateTypeEnum.kaika.getCode().equals(operateType)) {
                if (!JljsContractStatusEnum.daikaika.getCode().equals(contractStatus)) {
                    throw new BusinessException("合同不是待开卡状态，不可操作");
                }
                // 判断存在几个使用中的合同
                JljsContractInfo contractInfoInUse = jljsContractInfoService.getInUseContractInfoByMemberId(contractInfo.getMemberId());
                if (null != contractInfoInUse) {
                    throw new BusinessException("已经存在正在使用的合同,不可再次开卡");
                }
                if (null == operateRecord.getOperateBeginDate()) {
                    throw new BusinessException("开卡时间不能为空");
                }
                LambdaUpdateWrapper<JljsContractInfo> updateWrapper = Wrappers.lambdaUpdate(JljsContractInfo.class);
                updateWrapper.eq(BaseDO::getId, contractInfo.getId());
                updateWrapper.set(JljsContractInfo::getUseBeginDate, operateRecord.getOperateBeginDate());
                updateWrapper.set(JljsContractInfo::getContractStatus, JljsContractStatusEnum.shiyong.getCode());
                jljsContractInfoDao.update(updateWrapper);
                // 保存操作记录
                jljsContractOperateRecordDao.save(operateRecord);
                return ResultGenerator.success();
            }
            // 暂停 计算间隔时间 更新合同剩余有效天数/有效次数
            if (JljsContractOperateTypeEnum.zanting.getCode().equals(operateType)) {
                if (!StringUtils.equalsAny(contractStatus, JljsContractStatusEnum.shiyong.getCode(), JljsContractStatusEnum.zanting.getCode())) {
                    throw new BusinessException("只有使用中或者暂停中的合同才可以进行停课");
                }
                if (ObjectUtils.anyNull(operateRecord.getOperateBeginDate(), operateRecord.getOperateEndDate())) {
                    throw new BusinessException("起始时间不能为空");
                }
                // 获取该合同所有的成功的请假记录
                List<JljsContractOperateRecord> list = jljsContractOperateRecordDao.list(
                        Wrappers.lambdaQuery(JljsContractOperateRecord.class)
                                .eq(JljsContractOperateRecord::getContractInfoId, contractInfo.getId())
                                .eq(JljsContractOperateRecord::getOperateStatus, JljsOperateStatusEnum.chenggong.getCode())
                                .eq(JljsContractOperateRecord::getContractOperateType, JljsContractOperateTypeEnum.zanting.getCode())
                );
                if (operateRecord.getOperateBeginDate().before(contractInfo.getUseBeginDate())) {
                    throw new BusinessException("请假时间不能早于合同开始时间");
                }
                // 判断请假时间在不在合同有效期内
                if (!DateUtil.isIn(operateRecord.getOperateBeginDate(), contractInfo.getUseBeginDate(), contractInfo.getUseEndDate())
                        && !DateUtil.isIn(operateRecord.getOperateEndDate(), contractInfo.getUseBeginDate(), contractInfo.getUseEndDate())) {
                    throw new BusinessException("请假时间需要在合同起始时间内");
                }
                for (JljsContractOperateRecord record : list) {
                    Date operateBeginDate = record.getOperateBeginDate();
                    Date operateEndDate = record.getOperateEndDate();
                    // 请假起始时间在之前的请假记录内 或者 之前的请假起始时间包含了请假的时间
                    if (DateUtil.isIn(operateRecord.getOperateBeginDate(), operateBeginDate, operateEndDate)
                            || DateUtil.isIn(operateRecord.getOperateEndDate(), operateBeginDate, operateEndDate)
                            || DateUtil.isIn(operateBeginDate, operateRecord.getOperateBeginDate(), operateRecord.getOperateEndDate())
                            || DateUtil.isIn(operateEndDate, operateRecord.getOperateBeginDate(), operateRecord.getOperateEndDate())) {
                        throw new BusinessException("已存在" + DateUtil.formatDate(operateBeginDate) + "~" + DateUtil.formatDate(operateEndDate) + "请假记录，时间不可重复");
                    }
                }

                long dayDiff = DateUtil.betweenDay(operateRecord.getOperateBeginDate(), operateRecord.getOperateEndDate(), true) + 1;
                operateRecord.setIntervalDays((int) dayDiff);
                // 保存操作记录
                jljsContractOperateRecordDao.save(operateRecord);
                return ResultGenerator.success();
            }
            // 退课
            if (JljsContractOperateTypeEnum.tuike.getCode().equals(operateType)) {
                if (StringUtils.equalsAny(contractStatus, JljsContractStatusEnum.wancheng.getCode(), JljsContractStatusEnum.zhongzhi.getCode())) {
                    throw new BusinessException("合同已完成或已终止，不可操作");
                }
                if (null == operateRecord.getOperateAmount()) {
                    throw new BusinessException("退课金额不能为空");
                }
                // 保存操作记录
                jljsContractOperateRecordDao.save(operateRecord);
                // 更新合同状态 终止
                LambdaUpdateWrapper<JljsContractInfo> updateWrapper = Wrappers.lambdaUpdate(JljsContractInfo.class);
                updateWrapper.eq(BaseDO::getId, contractInfo.getId());
                updateWrapper.set(JljsContractInfo::getContractStatus, JljsContractStatusEnum.zhongzhi.getCode());
                jljsContractInfoDao.update(updateWrapper);
                return ResultGenerator.success();
            }
            // 补缴
            if (JljsContractOperateTypeEnum.bujiao.getCode().equals(operateType)) {
                if (null == operateRecord.getOperateAmount()) {
                    throw new BusinessException("补缴金额不能为空");
                }
                // 保存操作记录
                jljsContractOperateRecordDao.save(operateRecord);
                return ResultGenerator.success();
            }
        } finally {
            // 更新合同信息
            syncContractInfo.sync(operateRecord.getContractInfoId());
        }
        return ResultGenerator.fail("不支持的操作类型");
    }

    public Result<String> update(JljsContractOperateRecordUpdateVO updateVO) {
        jljsContractOperateRecordDao.updateById(commonConvert.updatetoJljsContractOperateRecord(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        jljsContractOperateRecordDao.removeById(id);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> revoke(String id) {
        JljsContractOperateRecord record = jljsContractOperateRecordDao.getById(id);
        if (null == record) {
            return ResultGenerator.fail("操作记录不存在");
        }
        // 更新为撤销状态
        record.setOperateStatus(JljsOperateStatusEnum.chexiao.getCode());
        jljsContractOperateRecordDao.updateById(record);

        // 撤销开卡 更新合同为待开卡 使用日期置空
        if (JljsContractOperateTypeEnum.kaika.getCode().equals(record.getContractOperateType())) {
            LambdaUpdateWrapper<JljsContractInfo> update = Wrappers.lambdaUpdate(JljsContractInfo.class);
            update.eq(BaseDO::getId, record.getContractInfoId());
            update.set(JljsContractInfo::getContractStatus, JljsContractStatusEnum.daikaika.getCode());
            update.set(JljsContractInfo::getUseBeginDate, null);
            jljsContractInfoDao.update(update);
        }

        syncContractInfo.sync(record.getContractInfoId());

        return ResultGenerator.success("操作成功");

    }
}