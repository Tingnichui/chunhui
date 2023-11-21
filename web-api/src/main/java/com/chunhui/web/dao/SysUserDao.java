package com.chunhui.web.dao;

import com.chunhui.web.mapper.SysUserMapper;
import com.chunhui.web.pojo.po.SysUser;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SysUserDao extends BaseDao<SysUserMapper, SysUser> {

    @Resource
    private SysUserMapper sysUserMapper;

    public String hello() {
        return sysUserMapper.hello();
    }
}
