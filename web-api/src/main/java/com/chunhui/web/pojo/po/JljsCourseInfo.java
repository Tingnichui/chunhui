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
     * 课程类型；1按次消费；2按天计时；
     */
    private String courseType;
    
    /**
     * 课程使用期限
     */
    private String courseUsePeriodDays;

    /**
     * 课程可使用数量
     */
    private String courseAvailableQuantity;


}