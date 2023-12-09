package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.SysRolePermissionMapper;
import com.chunhui.web.pojo.po.SysRolePermission;
import com.chunhui.web.pojo.query.SysRolePermissionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysRolePermissionDao extends BaseDao
<SysRolePermissionMapper, SysRolePermission> {

@Resource
private SysRolePermissionMapper sysRolePermissionMapper;

public IPage<SysRolePermission> pageListByQurey(SysRolePermissionQuery query) {
LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.like(StringUtils.isNotBlank(query.getRoleId()), SysRolePermission::getRoleId, query.getRoleId());
    queryWrapper.like(StringUtils.isNotBlank(query.getPermissionId()), SysRolePermission::getPermissionId, query.getPermissionId());
return super.pageList(query, queryWrapper);
}

}