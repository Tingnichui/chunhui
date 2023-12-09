package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.SysUserRoleMapper;
import com.chunhui.web.pojo.po.SysUserRole;
import com.chunhui.web.pojo.query.SysUserRoleQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysUserRoleDao extends BaseDao
<SysUserRoleMapper, SysUserRole> {

@Resource
private SysUserRoleMapper sysUserRoleMapper;

public IPage<SysUserRole> pageListByQurey(SysUserRoleQuery query) {
LambdaQueryWrapper<SysUserRole> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.like(StringUtils.isNotBlank(query.getUserId()), SysUserRole::getUserId, query.getUserId());
    queryWrapper.like(StringUtils.isNotBlank(query.getRoleId()), SysUserRole::getRoleId, query.getRoleId());
return super.pageList(query, queryWrapper);
}

}