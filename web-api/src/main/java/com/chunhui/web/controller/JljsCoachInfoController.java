package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.JljsCoachInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.JljsCoachInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/jljsCoachInfo")
public class JljsCoachInfoController {

@Resource
private JljsCoachInfoService jljsCoachInfoService;

@RequestMapping("/pageList")
public Result<PageResult<JljsCoachInfoOutVO>> pageList(@RequestBody JljsCoachInfoQuery query) {
    return jljsCoachInfoService.pageList(query);
    }

@RequestMapping("/detail")
public Result<JljsCoachInfoOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
return jljsCoachInfoService.detail(id);
}

@RequestMapping("/save")
public Result<String> save(@Validated @RequestBody JljsCoachInfoSaveVO saveVO) {
return jljsCoachInfoService.save(saveVO);
}


@RequestMapping("/update")
public Result<String> update(@Validated @RequestBody JljsCoachInfoUpdateVO updateVO) {
return jljsCoachInfoService.update(updateVO);
}

@RequestMapping("/delete")
public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
return jljsCoachInfoService.delete(id);
}


}
