package com.chunhui.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chunhui.web.pojo.po.ResInfo;
import com.chunhui.web.pojo.query.ResInfoQuery;
import com.chunhui.web.pojo.vo.ResInfoOutVO;
import com.chunhui.web.pojo.vo.ResInfoSaveVO;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.service.ResInfoService;
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

    @RequestMapping("/getResInfoById")
    public Result<ResInfoOutVO> detailById(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return resInfoService.getResInfoById(id);
    }

    @RequestMapping("/deleteById")
    public Result<ResInfoOutVO> deleteById(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return resInfoService.deleteById(id);
    }

    @RequestMapping("/pageList")
    public Result<IPage<ResInfo>> pageList(@RequestBody ResInfoQuery query) {
        return resInfoService.pageList(query);
    }

}