package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.JljsContractOperateRecordQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.JljsContractOperateRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/jljsContractOperateRecord")
public class JljsContractOperateRecordController {

@Resource
private JljsContractOperateRecordService jljsContractOperateRecordService;

@RequestMapping("/pageList")
public Result<PageResult<JljsContractOperateRecordOutVO>> pageList(@RequestBody JljsContractOperateRecordQuery query) {
    return jljsContractOperateRecordService.pageList(query);
    }

@RequestMapping("/detail")
public Result<JljsContractOperateRecordOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
return jljsContractOperateRecordService.detail(id);
}

@RequestMapping("/save")
public Result<String> save(@Validated @RequestBody JljsContractOperateRecordSaveVO saveVO) {
return jljsContractOperateRecordService.save(saveVO);
}


@RequestMapping("/update")
public Result<String> update(@Validated @RequestBody JljsContractOperateRecordUpdateVO updateVO) {
return jljsContractOperateRecordService.update(updateVO);
}

@RequestMapping("/delete")
public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
return jljsContractOperateRecordService.delete(id);
}


}
