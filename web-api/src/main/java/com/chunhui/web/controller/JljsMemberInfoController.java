package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.JljsMemberInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.jljs.JljsMemberInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/jljsMemberInfo")
public class JljsMemberInfoController {

    @Resource
    private JljsMemberInfoService jljsMemberInfoService;

    @RequestMapping("/pageList")
    public Result<PageResult<JljsMemberInfoOutVO>> pageList(@RequestBody JljsMemberInfoQuery query) {
        return jljsMemberInfoService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<JljsMemberInfoOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsMemberInfoService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@Validated @RequestBody JljsMemberInfoSaveVO saveVO) {
        return jljsMemberInfoService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@Validated @RequestBody JljsMemberInfoUpdateVO updateVO) {
        return jljsMemberInfoService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsMemberInfoService.delete(id);
    }


}
