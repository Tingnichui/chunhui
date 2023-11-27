package com.chunhui.web.mapstruct;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TypeConvert {

    @Named("toJsonString")
    public String toJsonString(Object obj) {
        if (null == obj) {
            return null;
        }
        return JSON.toJSONString(obj);
    }

    @Named("jsonStringToStringArray")
    public List<String> jsonStringToStringArray(String jsonStr) {
        if (StrUtil.isEmpty(jsonStr)) {
            return Collections.emptyList();
        }
        return JSON.parseArray(jsonStr, String.class);
    }

}
