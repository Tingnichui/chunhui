package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.JljsCourseInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.JljsCourseInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/jljsCourseInfo")
public class JljsCourseInfoController {

    @Resource
    private JljsCourseInfoService jljsCourseInfoService;

    @RequestMapping("/pageList")
    public Result<PageResult<JljsCourseInfoOutVO>> pageList(@RequestBody JljsCourseInfoQuery query) {
        return jljsCourseInfoService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<JljsCourseInfoOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsCourseInfoService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@Validated @RequestBody JljsCourseInfoSaveVO saveVO) {
        return jljsCourseInfoService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@Validated @RequestBody JljsCourseInfoUpdateVO updateVO) {
        return jljsCourseInfoService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsCourseInfoService.delete(id);
    }


}
