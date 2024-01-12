package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsCoachInfoMapper;
import com.chunhui.web.pojo.po.JljsCoachInfo;
import com.chunhui.web.pojo.query.JljsCoachInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsCoachInfoDao extends BaseDao
        <JljsCoachInfoMapper, JljsCoachInfo> {

    @Resource
    private JljsCoachInfoMapper jljsCoachInfoMapper;

    public IPage<JljsCoachInfo> pageListByQurey(JljsCoachInfoQuery query) {
        LambdaQueryWrapper<JljsCoachInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getCoachName()), JljsCoachInfo::getCoachName, query.getCoachName());
        queryWrapper.like(StringUtils.isNotBlank(query.getCoachPhoneNum()), JljsCoachInfo::getCoachPhoneNum, query.getCoachPhoneNum());
        return super.pageList(query, queryWrapper);
    }

}