package com.chunhui.web.controller;


import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.service.SysUserService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public String hello() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return sysUserService.hello();
    }

    @RequestMapping("/detail")
    public SysUser detail() {
        return sysUserService.detail();
    }

    @RequestMapping("/login")
    public Result<String> login() {
        return sysUserService.login();
    }

    @RequestMapping("/logout")
    public Result<String> logout(@RequestHeader String token) {
        return sysUserService.logout(token);
    }



}