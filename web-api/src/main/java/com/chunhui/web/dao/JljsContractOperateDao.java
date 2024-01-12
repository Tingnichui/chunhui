package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsContractOperateMapper;
import com.chunhui.web.pojo.po.JljsContractOperate;
import com.chunhui.web.pojo.query.JljsContractOperateQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsContractOperateDao extends BaseDao
        <JljsContractOperateMapper, JljsContractOperate> {

    @Resource
    private JljsContractOperateMapper jljsContractOperateMapper;

    public IPage<JljsContractOperate> pageListByQurey(JljsContractOperateQuery query) {
        LambdaQueryWrapper<JljsContractOperate> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getCourseInfoId()), JljsContractOperate::getCourseInfoId, query.getCourseInfoId());
        queryWrapper.like(StringUtils.isNotBlank(query.getOperateType()), JljsContractOperate::getOperateType, query.getOperateType());
//        queryWrapper.like(StringUtils.isNotBlank(query.getIntervalDays()), JljsContractOperate::getIntervalDays, query.getIntervalDays());
        queryWrapper.like(StringUtils.isNotBlank(query.getOperateBeginDate()), JljsContractOperate::getOperateBeginDate, query.getOperateBeginDate());
        queryWrapper.like(StringUtils.isNotBlank(query.getOperateEndDate()), JljsContractOperate::getOperateEndDate, query.getOperateEndDate());
        queryWrapper.like(StringUtils.isNotBlank(query.getOperateReason()), JljsContractOperate::getOperateReason, query.getOperateReason());
        queryWrapper.like(StringUtils.isNotBlank(query.getOperateAmount()), JljsContractOperate::getOperateAmount, query.getOperateAmount());
        return super.pageList(query, queryWrapper);
    }

}