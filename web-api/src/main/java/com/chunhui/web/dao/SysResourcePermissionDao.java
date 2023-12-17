package com.chunhui.web.dao;

import com.chunhui.web.mapper.SysResourcePermissionMapper;
import com.chunhui.web.pojo.po.SysResourcePermission;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysResourcePermissionDao extends BaseDao<SysResourcePermissionMapper, SysResourcePermission> {

    @Resource
    private SysResourcePermissionMapper sysResourcePermissionMapper;

}