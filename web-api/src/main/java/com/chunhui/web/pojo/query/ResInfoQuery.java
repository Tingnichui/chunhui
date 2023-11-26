package com.chunhui.web.pojo.query;

import lombok.Data;

@Data
public class ResInfoQuery extends QueryBase {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

}
