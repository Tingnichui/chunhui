package com.chunhui.web.pojo.query;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class JljsCourseInfoQuery extends QueryBase {

    private String id;


    /**
     * 名称
     */
    private String courseName;


    /**
     * 价格
     */
    private BigDecimal coursePrice;


    /**
     * 描述
     */
    private String courseDescribe;


    /**
     * 有效天数
     */
    private Integer courseValidDays;


}
