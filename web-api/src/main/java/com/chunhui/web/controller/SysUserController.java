package com.chunhui.web.controller;


import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.query.SysUserQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public String hello() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return sysUserService.hello();
    }

    @RequestMapping("/regist")
    public Result<String> regist(@RequestBody SysUser sysUser) {
        return sysUserService.regist(sysUser);
    }

    @RequestMapping("/login")
    public Result<String> login(@RequestBody SysUser sysUser) {
        return sysUserService.login(sysUser);
    }

    @RequestMapping("/logout")
    public Result<String> logout(@RequestHeader String token) {
        return sysUserService.logout(token);
    }

    @RequestMapping("/pageList")
    public Result<PageResult<SysUserOutVO>> regist(@RequestBody SysUserQuery query) {
        return sysUserService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<SysUserOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysUserService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@RequestBody SysUserSaveVO saveVO) {
        return sysUserService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@RequestBody SysUserUpdateVO updateVO) {
        return sysUserService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysUserService.delete(id);
    }


}
