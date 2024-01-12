package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.JljsMemberOrderQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.JljsMemberOrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/jljsMemberOrder")
public class JljsMemberOrderController {

    @Resource
    private JljsMemberOrderService jljsMemberOrderService;

    @RequestMapping("/pageList")
    public Result<PageResult<JljsMemberOrderOutVO>> regist(@RequestBody JljsMemberOrderQuery query) {
        return jljsMemberOrderService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<JljsMemberOrderOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsMemberOrderService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@RequestBody JljsMemberOrderSaveVO saveVO) {
        return jljsMemberOrderService.save(saveVO);
    }


    @RequestMapping("/update")
    public Result<String> update(@RequestBody JljsMemberOrderUpdateVO updateVO) {
        return jljsMemberOrderService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsMemberOrderService.delete(id);
    }


}
