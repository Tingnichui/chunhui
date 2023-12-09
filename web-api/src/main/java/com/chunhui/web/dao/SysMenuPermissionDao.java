package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.SysMenuPermissionMapper;
import com.chunhui.web.pojo.po.SysMenuPermission;
import com.chunhui.web.pojo.query.SysMenuPermissionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysMenuPermissionDao extends BaseDao
<SysMenuPermissionMapper, SysMenuPermission> {

@Resource
private SysMenuPermissionMapper sysMenuPermissionMapper;

public IPage<SysMenuPermission> pageListByQurey(SysMenuPermissionQuery query) {
LambdaQueryWrapper<SysMenuPermission> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.like(StringUtils.isNotBlank(query.getMenuId()), SysMenuPermission::getMenuId, query.getMenuId());
    queryWrapper.like(StringUtils.isNotBlank(query.getPermissionId()), SysMenuPermission::getPermissionId, query.getPermissionId());
return super.pageList(query, queryWrapper);
}

}