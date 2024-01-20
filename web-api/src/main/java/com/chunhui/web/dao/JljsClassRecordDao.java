package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsClassRecordMapper;
import com.chunhui.web.pojo.po.JljsClassRecord;
import com.chunhui.web.pojo.query.JljsClassRecordQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsClassRecordDao extends BaseDao
        <JljsClassRecordMapper, JljsClassRecord> {

    @Resource
    private JljsClassRecordMapper jljsClassRecordMapper;

    public IPage<JljsClassRecord> pageListByQurey(JljsClassRecordQuery query) {
        LambdaQueryWrapper<JljsClassRecord> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getCoachId()), JljsClassRecord::getCoachId, query.getCoachId());
        queryWrapper.like(StringUtils.isNotBlank(query.getMemberId()), JljsClassRecord::getMemberId, query.getMemberId());
//    queryWrapper.like(StringUtils.isNotBlank(query.getClassBeginTime()), JljsClassRecord::getClassBeginTime, query.getClassBeginTime());
//    queryWrapper.like(StringUtils.isNotBlank(query.getClassEndTime()), JljsClassRecord::getClassEndTime, query.getClassEndTime());
        queryWrapper.like(StringUtils.isNotBlank(query.getClassRemark()), JljsClassRecord::getClassRemark, query.getClassRemark());
        queryWrapper.orderByDesc(JljsClassRecord::getClassBeginTime);
        return super.pageList(query, queryWrapper);
    }

}