package com.chunhui.web.controller;

import com.chunhui.web.pojo.query.ResInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.ResInfoService;
import com.chunhui.web.util.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * res_info 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@Validated
@RestController
@RequestMapping("/resInfo")
public class ResInfoController {

    @Resource
    private ResInfoService resInfoService;

    @RequestMapping("/save")
    public Result<String> save(@Validated @RequestBody ResInfoSaveVO resInfo) {
        return resInfoService.save(resInfo);
    }

    @RequestMapping("/update")
    public Result<String> update(@Validated @RequestBody ResInfoUpdateVO resInfo) {
        if (StringUtils.isBlank(resInfo.getId())) {
            return ResultGenerator.fail("id不能为空");
        }
        return resInfoService.update(resInfo);
    }

    @RequestMapping("/pageList")
    public Result<PageResult<ResInfoOutVO>> pageList(@RequestBody ResInfoQuery query) {
        return resInfoService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<ResInfoOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return resInfoService.detail(id);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return resInfoService.delete(id);
    }

}