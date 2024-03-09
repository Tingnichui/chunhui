package com.chunhui.web.mapper;

import java.util.List;
import java.util.Map;

public interface JljsStatsMapper {
    List<Map<String, Object>> getClassStatsByDay(Map<String, Object> params);


    List<Map<String, Object>> getMemberBirthStats(Map<String, Object> params);
}
