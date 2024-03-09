package com.chunhui.web.service.jljs;


import com.chunhui.web.mapper.JljsStatsMapper;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class JljsStatsService {

    @Resource
    private JljsStatsMapper jljsStatsMapper;

    public Result<Object> getClassStatsByDay(Map<String, Object> params) {
        return ResultGenerator.success(jljsStatsMapper.getClassStatsByDay(params));
    }
}
