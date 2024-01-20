package com.chunhui.web.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class JljsClassRecordQuery extends QueryBase {

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date classBeginTime;


    /**
     * 课程结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date classEndTime;


    /**
     * 课程备注
     */
    private String classRemark;


}
