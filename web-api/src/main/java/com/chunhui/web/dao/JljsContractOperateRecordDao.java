package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsContractOperateRecordMapper;
import com.chunhui.web.pojo.po.BaseDO;
import com.chunhui.web.pojo.po.JljsContractOperateRecord;
import com.chunhui.web.pojo.query.JljsContractOperateRecordQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsContractOperateRecordDao extends BaseDao
        <JljsContractOperateRecordMapper, JljsContractOperateRecord> {

    @Resource
    private JljsContractOperateRecordMapper jljsContractOperateRecordMapper;

    public IPage<JljsContractOperateRecord> pageListByQurey(JljsContractOperateRecordQuery query) {
        LambdaQueryWrapper<JljsContractOperateRecord> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotBlank(query.getContractInfoId()), JljsContractOperateRecord::getContractInfoId, query.getContractInfoId());
        queryWrapper.eq(StringUtils.isNotBlank(query.getContractOperateType()), JljsContractOperateRecord::getContractOperateType, query.getContractOperateType());
//    queryWrapper.like(StringUtils.isNotBlank(query.getIntervalDays()), JljsContractOperateRecord::getIntervalDays, query.getIntervalDays());
//        queryWrapper.like(StringUtils.isNotBlank(query.getOperateBeginDate()), JljsContractOperateRecord::getOperateBeginDate, query.getOperateBeginDate());
//        queryWrapper.like(StringUtils.isNotBlank(query.getOperateEndDate()), JljsContractOperateRecord::getOperateEndDate, query.getOperateEndDate());
//        queryWrapper.like(StringUtils.isNotBlank(query.getOperateReason()), JljsContractOperateRecord::getOperateReason, query.getOperateReason());
//        queryWrapper.like(StringUtils.isNotBlank(query.getOperateAmount()), JljsContractOperateRecord::getOperateAmount, query.getOperateAmount());
        queryWrapper.orderByDesc(BaseDO::getCreateTime);
        return super.pageList(query, queryWrapper);
    }

}