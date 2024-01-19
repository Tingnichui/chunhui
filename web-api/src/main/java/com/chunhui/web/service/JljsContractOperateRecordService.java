package com.chunhui.web.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.constants.jljs.JljsContractOperateTypeEnum;
import com.chunhui.web.dao.JljsContractInfoDao;
import com.chunhui.web.dao.JljsContractOperateRecordDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.BaseDO;
import com.chunhui.web.pojo.po.JljsContractInfo;
import com.chunhui.web.pojo.po.JljsContractOperateRecord;
import com.chunhui.web.pojo.query.JljsContractOperateRecordQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class JljsContractOperateRecordService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsContractOperateRecordDao jljsContractOperateRecordDao;
    private JljsContractInfoDao jljsContractInfoDao;

    public Result<PageResult<JljsContractOperateRecordOutVO>> pageList(JljsContractOperateRecordQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsContractOperateRecordDao.pageListByQurey(query), commonConvert::toJljsContractOperateRecordOutList));
    }

    public Result<JljsContractOperateRecordOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsContractOperateRecordListOut(jljsContractOperateRecordDao.getById(id)));
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(JljsContractOperateRecordSaveVO saveVO) {
        JljsContractOperateRecord operateRecord = commonConvert.toJljsContractOperateRecord(saveVO);
        JljsContractInfo contractInfo = jljsContractInfoDao.getById(operateRecord.getContractInfoId());
        if (null == contractInfo) {
            return ResultGenerator.fail("合同不存在");
        }

        String contractStatus = contractInfo.getContractStatus();


        LambdaUpdateWrapper<JljsContractInfo> update = Wrappers.lambdaUpdate(JljsContractInfo.class);
        update.eq(BaseDO::getId, contractInfo.getId());
        // 开卡
        if (JljsContractOperateTypeEnum.kaika.getCode().equals(operateRecord.getContractOperateType())) {
            update.set(JljsContractInfo::getUseBeginDate, operateRecord.getOperateBeginDate());
        }
        // 暂停
        if (JljsContractOperateTypeEnum.zanting.getCode().equals(operateRecord.getContractOperateType())) {

        }
        // 退课
        if (JljsContractOperateTypeEnum.tuike.getCode().equals(operateRecord.getContractOperateType())) {
        }
        // 补缴
        if (JljsContractOperateTypeEnum.bujiao.getCode().equals(operateRecord.getContractOperateType())) {
        }
        jljsContractOperateRecordDao.save(operateRecord);
        return ResultGenerator.success();
    }

    public Result<String> update(JljsContractOperateRecordUpdateVO updateVO) {
        jljsContractOperateRecordDao.updateById(commonConvert.updatetoJljsContractOperateRecord(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        jljsContractOperateRecordDao.removeById(id);
        return ResultGenerator.success();
    }

}