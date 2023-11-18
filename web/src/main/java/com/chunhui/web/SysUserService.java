package com.chunhui.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.dao.SysUserDao;
import com.chunhui.web.pojo.po.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    public String hello() {
        System.err.println(sysUserDao.list(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getId,"1")));
        return sysUserDao.hello();
    }
}
