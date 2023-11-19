package com.chunhui.web.controller;


import com.chunhui.web.service.SysUserService;
import com.chunhui.web.pojo.po.SysUser;
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
    public String login() {
        return "login";
    }



}
