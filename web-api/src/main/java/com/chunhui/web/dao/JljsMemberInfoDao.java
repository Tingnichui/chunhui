package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.JljsMemberInfoMapper;
import com.chunhui.web.pojo.po.BaseDO;
import com.chunhui.web.pojo.po.JljsMemberInfo;
import com.chunhui.web.pojo.query.JljsMemberInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class JljsMemberInfoDao extends BaseDao
        <JljsMemberInfoMapper, JljsMemberInfo> {

    @Resource
    private JljsMemberInfoMapper jljsMemberInfoMapper;

    public IPage<JljsMemberInfo> pageListByQurey(JljsMemberInfoQuery query) {
        LambdaQueryWrapper<JljsMemberInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getMemberName()), JljsMemberInfo::getMemberName, query.getMemberName());
        queryWrapper.like(StringUtils.isNotBlank(query.getMemberGender()), JljsMemberInfo::getMemberGender, query.getMemberGender());
        queryWrapper.like(StringUtils.isNotBlank(query.getMemberAge()), JljsMemberInfo::getMemberAge, query.getMemberAge());
        queryWrapper.like(StringUtils.isNotBlank(query.getMemberPhoneNum()), JljsMemberInfo::getMemberPhoneNum, query.getMemberPhoneNum());
        queryWrapper.orderByDesc(BaseDO::getCreateTime);
        return super.pageList(query, queryWrapper);
    }

}