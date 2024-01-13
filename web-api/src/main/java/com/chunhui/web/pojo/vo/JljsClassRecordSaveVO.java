package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
public class JljsClassRecordSaveVO {


    /**
     * 教练id
     */
    @NotBlank(message = "上课教练不能为空")
    private String coachId;


    /**
     * 会员id
     */
    @NotBlank(message = "会员不能为空")
    private String memberId;


    /**
     * 课程开始时间
     */
    @NotBlank(message = "课程开始时间不能为空")
    private Date classBeginTime;


    /**
     * 课程结束时间
     */
    @NotBlank(message = "课程结束时间不能为空")
    private Date classEndTime;


    /**
     * 课程备注
     */
    private String classRemark;


}
