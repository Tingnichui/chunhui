package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsContractInfoMapper;
import com.chunhui.web.pojo.po.BaseDO;
import com.chunhui.web.pojo.po.JljsContractInfo;
import com.chunhui.web.pojo.query.JljsContractInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsContractInfoDao extends BaseDao<JljsContractInfoMapper, JljsContractInfo> {

    @Resource
    private JljsContractInfoMapper jljsContractInfoMapper;

    public IPage<JljsContractInfo> pageListByQurey(JljsContractInfoQuery query) {
        LambdaQueryWrapper<JljsContractInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotBlank(query.getMemberId()), JljsContractInfo::getMemberId, query.getMemberId());
        queryWrapper.eq(StringUtils.isNotBlank(query.getCourseInfoId()), JljsContractInfo::getCourseInfoId, query.getCourseInfoId());
        queryWrapper.eq(StringUtils.isNotBlank(query.getBelongCoachId()), JljsContractInfo::getBelongCoachId, query.getBelongCoachId());
        queryWrapper.eq(StringUtils.isNotBlank(query.getContractStatus()), JljsContractInfo::getContractStatus, query.getContractStatus());
        queryWrapper.like(StringUtils.isNotBlank(query.getContractRemark()), JljsContractInfo::getContractRemark, query.getContractRemark());
        queryWrapper.orderByDesc(BaseDO::getCreateTime);
        return super.pageList(query, queryWrapper);
    }

}