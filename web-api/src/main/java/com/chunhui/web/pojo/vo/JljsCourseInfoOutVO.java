package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JljsCourseInfoOutVO {


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
     * 课程类型；1按次消费；2按天计时；
     */
    private String courseType;

    /**
     * 课程使用期限
     */
    private Integer courseUsePeriodDays;

    /**
     * 课程可使用数量
     */
    private Integer courseAvailableQuantity;


}
