package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class JljsClassRecordOutVO {


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
