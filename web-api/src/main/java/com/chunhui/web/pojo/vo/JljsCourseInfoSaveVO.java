package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class JljsCourseInfoSaveVO {


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
