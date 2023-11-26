package com.chunhui.web.pojo.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryBase {
    /**
     * 当前页面
     */
    private long current = 1;

    /**
     * 每页显示条数
     */
    private long size = 10;

}
