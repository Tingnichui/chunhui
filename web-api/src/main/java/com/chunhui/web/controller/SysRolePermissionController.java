package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.SysRolePermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.SysRolePermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController {

@Resource
private SysRolePermissionService sysRolePermissionService;

@RequestMapping("/pageList")
public Result<PageResult<SysRolePermissionOutVO>> regist(@RequestBody SysRolePermissionQuery query) {
    return sysRolePermissionService.pageList(query);
    }

@RequestMapping("/detail")
public Result<SysRolePermissionOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
return sysRolePermissionService.detail(id);
}

@RequestMapping("/save")
public Result<String> save(@RequestBody SysRolePermissionSaveVO saveVO) {
return sysRolePermissionService.save(saveVO);
}


@RequestMapping("/update")
public Result<String> update(@RequestBody SysRolePermissionUpdateVO updateVO) {
return sysRolePermissionService.update(updateVO);
}

@RequestMapping("/delete")
public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
return sysRolePermissionService.delete(id);
}


}
