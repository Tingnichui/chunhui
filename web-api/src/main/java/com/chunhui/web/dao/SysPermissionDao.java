package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.SysPermissionMapper;
import com.chunhui.web.pojo.po.SysPermission;
import com.chunhui.web.pojo.query.SysPermissionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysPermissionDao extends BaseDao
<SysPermissionMapper, SysPermission> {

@Resource
private SysPermissionMapper sysPermissionMapper;

public IPage<SysPermission> pageListByQurey(SysPermissionQuery query) {
LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.like(StringUtils.isNotBlank(query.getName()), SysPermission::getName, query.getName());
    queryWrapper.like(StringUtils.isNotBlank(query.getPermissionType()), SysPermission::getPermissionType, query.getPermissionType());
return super.pageList(query, queryWrapper);
}

}