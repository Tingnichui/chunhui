package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsMemberOrderMapper;
import com.chunhui.web.pojo.po.JljsMemberOrder;
import com.chunhui.web.pojo.query.JljsMemberOrderQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsMemberOrderDao extends BaseDao
        <JljsMemberOrderMapper, JljsMemberOrder> {

    @Resource
    private JljsMemberOrderMapper jljsMemberOrderMapper;

    public IPage<JljsMemberOrder> pageListByQurey(JljsMemberOrderQuery query) {
        LambdaQueryWrapper<JljsMemberOrder> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getMemberId()), JljsMemberOrder::getMemberId, query.getMemberId());
        queryWrapper.like(StringUtils.isNotBlank(query.getCourseInfoId()), JljsMemberOrder::getCourseInfoId, query.getCourseInfoId());
        queryWrapper.like(StringUtils.isNotBlank(query.getBelongCoachId()), JljsMemberOrder::getBelongCoachId, query.getBelongCoachId());
//        queryWrapper.like(StringUtils.isNotBlank(query.getContractAmount()), JljsMemberOrder::getContractAmount, query.getContractAmount());
        queryWrapper.like(StringUtils.isNotBlank(query.getContractStatus()), JljsMemberOrder::getContractStatus, query.getContractStatus());
        queryWrapper.like(StringUtils.isNotBlank(query.getUseBeginDate()), JljsMemberOrder::getUseBeginDate, query.getUseBeginDate());
        queryWrapper.like(StringUtils.isNotBlank(query.getUseEndDate()), JljsMemberOrder::getUseEndDate, query.getUseEndDate());
        queryWrapper.like(StringUtils.isNotBlank(query.getBuyTime()), JljsMemberOrder::getBuyTime, query.getBuyTime());
        queryWrapper.like(StringUtils.isNotBlank(query.getContractRemark()), JljsMemberOrder::getContractRemark, query.getContractRemark());
        queryWrapper.like(StringUtils.isNotBlank(query.getActualChargeAmount()), JljsMemberOrder::getActualChargeAmount, query.getActualChargeAmount());
        return super.pageList(query, queryWrapper);
    }

}