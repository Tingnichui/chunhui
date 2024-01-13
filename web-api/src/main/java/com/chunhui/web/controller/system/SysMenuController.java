package com.chunhui.web.controller.system;


import com.chunhui.web.pojo.query.SysMenuQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.SysMenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping("/pageList")
    public Result<PageResult<SysMenuOutVO>> regist(@RequestBody SysMenuQuery query) {
        return sysMenuService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<SysMenuOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysMenuService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@RequestBody SysMenuSaveVO saveVO) {
        return sysMenuService.save(saveVO);
    }

    @RequestMapping("/getMenuByCurrentUser")
    public Result<List<SysMenuOutVO>> getMenuByCurrentUser() throws Exception {
        return sysMenuService.getMenuByCurrentUser();
    }


    @RequestMapping("/update")
    public Result<String> update(@RequestBody SysMenuUpdateVO updateVO) {
        return sysMenuService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return sysMenuService.delete(id);
    }


}
