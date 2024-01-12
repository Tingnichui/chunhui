package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.JljsContractInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.JljsContractInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/jljsContractInfo")
public class JljsContractInfoController {

@Resource
private JljsContractInfoService jljsContractInfoService;

@RequestMapping("/pageList")
public Result<PageResult<JljsContractInfoOutVO>> regist(@RequestBody JljsContractInfoQuery query) {
    return jljsContractInfoService.pageList(query);
    }

@RequestMapping("/detail")
public Result<JljsContractInfoOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
return jljsContractInfoService.detail(id);
}

@RequestMapping("/save")
public Result<String> save(@RequestBody JljsContractInfoSaveVO saveVO) {
return jljsContractInfoService.save(saveVO);
}


@RequestMapping("/update")
public Result<String> update(@RequestBody JljsContractInfoUpdateVO updateVO) {
return jljsContractInfoService.update(updateVO);
}

@RequestMapping("/delete")
public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
return jljsContractInfoService.delete(id);
}


}
