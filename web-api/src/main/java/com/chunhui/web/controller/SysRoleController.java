package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.SysRoleQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.SysRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/pageList")
    public Result<PageResult<SysRoleOutVO>> regist(@RequestBody SysRoleQuery query) {
        return sysRoleService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<SysRoleOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysRoleService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@RequestBody SysRoleSaveVO saveVO) {
        return sysRoleService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@RequestBody SysRoleUpdateVO updateVO) {
        return sysRoleService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysRoleService.delete(id);
    }


}
