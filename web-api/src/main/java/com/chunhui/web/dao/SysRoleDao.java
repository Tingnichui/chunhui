package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.SysRoleMapper;
import com.chunhui.web.pojo.po.SysRole;
import com.chunhui.web.pojo.query.SysRoleQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysRoleDao extends BaseDao
        <SysRoleMapper, SysRole> {

    @Resource
    private SysRoleMapper sysRoleMapper;

    public IPage<SysRole> pageListByQurey(SysRoleQuery query) {
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getRoleName()), SysRole::getRoleName, query.getRoleName());
        return super.pageList(query, queryWrapper);
    }

}