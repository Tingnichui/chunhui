package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.JljsContractOperateQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.JljsContractOperateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/jljsContractOperate")
public class JljsContractOperateController {

    @Resource
    private JljsContractOperateService jljsContractOperateService;

    @RequestMapping("/pageList")
    public Result<PageResult<JljsContractOperateOutVO>> regist(@RequestBody JljsContractOperateQuery query) {
        return jljsContractOperateService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<JljsContractOperateOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsContractOperateService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@RequestBody JljsContractOperateSaveVO saveVO) {
        return jljsContractOperateService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@RequestBody JljsContractOperateUpdateVO updateVO) {
        return jljsContractOperateService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsContractOperateService.delete(id);
    }


}
