package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 加励健身-课程信息
 */
@Data
@TableName("jljs_course_info")
public class JljsCourseInfo extends BaseDO {


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