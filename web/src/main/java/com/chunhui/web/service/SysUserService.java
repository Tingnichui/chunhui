package com.chunhui.web.service;

import com.chunhui.web.dao.SysUserDao;
import com.chunhui.web.pojo.po.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    public String hello() {
        return sysUserDao.hello();
    }

    public SysUser detail() {
        return sysUserDao.getById("1");
    }

}
