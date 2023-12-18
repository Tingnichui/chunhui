package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.SysUserRoleQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.SysUserRoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/pageList")
    public Result<PageResult<SysUserRoleOutVO>> regist(@RequestBody SysUserRoleQuery query) {
        return sysUserRoleService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<SysUserRoleOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysUserRoleService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@Validated @RequestBody SysUserRoleSaveVO saveVO) {
        return sysUserRoleService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@RequestBody SysUserRoleUpdateVO updateVO) {
        return sysUserRoleService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysUserRoleService.delete(id);
    }


}
