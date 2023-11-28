package com.chunhui.web.controller;

import com.chunhui.web.pojo.query.ResInfoQuery;
import com.chunhui.web.pojo.vo.PageResult;
import com.chunhui.web.pojo.vo.ResInfoOutVO;
import com.chunhui.web.pojo.vo.ResInfoSaveVO;
import com.chunhui.web.pojo.vo.Result;
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
    public Result<String> update(@Validated @RequestBody ResInfoSaveVO resInfo) {
        if (StringUtils.isBlank(resInfo.getId())) {
            return ResultGenerator.fail("id不能为空");
        }
        return resInfoService.update(resInfo);
    }

    @RequestMapping("/getResInfoById")
    public Result<ResInfoOutVO> detailById(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return resInfoService.getResInfoById(id);
    }

    @RequestMapping("/deleteById")
    public Result<ResInfoOutVO> deleteById(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return resInfoService.deleteById(id);
    }

    @RequestMapping("/pageList")
    public Result<PageResult<ResInfoOutVO>> pageList(@RequestBody ResInfoQuery query) {
        return resInfoService.pageList(query);
    }

}