package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.SysMenuPermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.SysMenuPermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/sysMenuPermission")
public class SysMenuPermissionController {

@Resource
private SysMenuPermissionService sysMenuPermissionService;

@RequestMapping("/pageList")
public Result<PageResult<SysMenuPermissionOutVO>> regist(@RequestBody SysMenuPermissionQuery query) {
    return sysMenuPermissionService.pageList(query);
    }

@RequestMapping("/detail")
public Result<SysMenuPermissionOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
return sysMenuPermissionService.detail(id);
}

@RequestMapping("/save")
public Result<String> save(@RequestBody SysMenuPermissionSaveVO saveVO) {
return sysMenuPermissionService.save(saveVO);
}


@RequestMapping("/update")
public Result<String> update(@RequestBody SysMenuPermissionUpdateVO updateVO) {
return sysMenuPermissionService.update(updateVO);
}

@RequestMapping("/delete")
public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
return sysMenuPermissionService.delete(id);
}


}
