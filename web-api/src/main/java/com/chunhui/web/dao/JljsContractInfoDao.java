package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsContractInfoMapper;
import com.chunhui.web.pojo.po.JljsContractInfo;
import com.chunhui.web.pojo.query.JljsContractInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsContractInfoDao extends BaseDao
        <JljsContractInfoMapper, JljsContractInfo> {

    @Resource
    private JljsContractInfoMapper jljsContractInfoMapper;

    public IPage<JljsContractInfo> pageListByQurey(JljsContractInfoQuery query) {
        LambdaQueryWrapper<JljsContractInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getMemberId()), JljsContractInfo::getMemberId, query.getMemberId());
        queryWrapper.like(StringUtils.isNotBlank(query.getCourseInfoId()), JljsContractInfo::getCourseInfoId, query.getCourseInfoId());
        queryWrapper.like(StringUtils.isNotBlank(query.getBelongCoachId()), JljsContractInfo::getBelongCoachId, query.getBelongCoachId());
//    queryWrapper.like(StringUtils.isNotBlank(query.getContractAmount()), JljsContractInfo::getContractAmount, query.getContractAmount());
        queryWrapper.like(StringUtils.isNotBlank(query.getContractStatus()), JljsContractInfo::getContractStatus, query.getContractStatus());
        queryWrapper.like(StringUtils.isNotBlank(query.getUseBeginDate()), JljsContractInfo::getUseBeginDate, query.getUseBeginDate());
        queryWrapper.like(StringUtils.isNotBlank(query.getUseEndDate()), JljsContractInfo::getUseEndDate, query.getUseEndDate());
        queryWrapper.like(StringUtils.isNotBlank(query.getBuyTime()), JljsContractInfo::getBuyTime, query.getBuyTime());
        queryWrapper.like(StringUtils.isNotBlank(query.getContractRemark()), JljsContractInfo::getContractRemark, query.getContractRemark());
        queryWrapper.like(StringUtils.isNotBlank(query.getActualChargeAmount()), JljsContractInfo::getActualChargeAmount, query.getActualChargeAmount());
        return super.pageList(query, queryWrapper);
    }

}