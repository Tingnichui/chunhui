package com.chunhui.web.mapper;

import java.util.List;
import java.util.Map;

public interface JljsStatsMapper {
    List<Map<String, Object>> getClassStatsByDay(Map<String, Object> params);


}
