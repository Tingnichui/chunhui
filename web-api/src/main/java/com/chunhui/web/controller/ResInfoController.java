package com.chunhui.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chunhui.web.pojo.po.ResInfo;
import com.chunhui.web.pojo.query.ResInfoQuery;
import com.chunhui.web.pojo.vo.ResInfoVO;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.service.ResInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * res_info 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@RestController
@RequestMapping("/resInfo")
public class ResInfoController {

    @Resource
    private ResInfoService resInfoService;

    @RequestMapping("/save")
    public Result<String> save(@Validated @RequestBody ResInfoVO resInfo) {
        return resInfoService.save(resInfo);
    }

    @RequestMapping("/pageList")
    public Result<IPage<ResInfo>> pageList(@RequestBody ResInfoQuery query) {
        return resInfoService.pageList(query);
    }

}