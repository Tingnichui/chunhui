package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.SysPermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.SysPermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @RequestMapping("/pageList")
    public Result<PageResult<SysPermissionOutVO>> regist(@RequestBody SysPermissionQuery query) {
        return sysPermissionService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<SysPermissionOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysPermissionService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@RequestBody SysPermissionSaveVO saveVO) {
        return sysPermissionService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@RequestBody SysPermissionUpdateVO updateVO) {
        return sysPermissionService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysPermissionService.delete(id);
    }


}
