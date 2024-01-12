package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
public class JljsClassRecordUpdateVO {


    @NotBlank(message = "主键id不能为空")
    private String id;


    /**
     * 教练id
     */
    private String coachId;


    /**
     * 会员id
     */
    private String memberId;


    /**
     * 课程开始时间
     */
    private Date classBeginTime;


    /**
     * 课程结束时间
     */
    private Date classEndTime;


    /**
     * 课程备注
     */
    private String classRemark;


}
