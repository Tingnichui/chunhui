package com.chunhui.web.controller;


import com.chunhui.web.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public String hello() {
        return sysUserService.hello();
    }

}
