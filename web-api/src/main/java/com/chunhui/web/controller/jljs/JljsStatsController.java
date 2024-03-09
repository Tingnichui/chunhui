package com.chunhui.web.controller.jljs;


import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.service.jljs.JljsStatsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/jljsStats")
public class JljsStatsController {

    @Resource
    private JljsStatsService jljsStatsService;

    @RequestMapping("/getClassStatsByDay")
    public Result<Object> getClassStatsByDay(@RequestBody(required = false) Map<String, Object> params) {
        return jljsStatsService.getClassStatsByDay(params);
    }


}
