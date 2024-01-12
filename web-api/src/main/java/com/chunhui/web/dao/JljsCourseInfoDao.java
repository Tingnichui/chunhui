package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsCourseInfoMapper;
import com.chunhui.web.pojo.po.JljsCourseInfo;
import com.chunhui.web.pojo.query.JljsCourseInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsCourseInfoDao extends BaseDao
        <JljsCourseInfoMapper, JljsCourseInfo> {

    @Resource
    private JljsCourseInfoMapper jljsCourseInfoMapper;

    public IPage<JljsCourseInfo> pageListByQurey(JljsCourseInfoQuery query) {
        LambdaQueryWrapper<JljsCourseInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getCourseName()), JljsCourseInfo::getCourseName, query.getCourseName());
//        queryWrapper.like(StringUtils.isNotBlank(query.getCoursePrice()), JljsCourseInfo::getCoursePrice, query.getCoursePrice());
        queryWrapper.like(StringUtils.isNotBlank(query.getCourseDescribe()), JljsCourseInfo::getCourseDescribe, query.getCourseDescribe());
//        queryWrapper.like(StringUtils.isNotBlank(query.getCourseValidDays()), JljsCourseInfo::getCourseValidDays, query.getCourseValidDays());
        return super.pageList(query, queryWrapper);
    }

}