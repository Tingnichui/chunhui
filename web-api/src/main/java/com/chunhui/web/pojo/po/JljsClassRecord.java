package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 加励健身-上课记录
 */
@Data
@TableName("jljs_class_record")
public class JljsClassRecord extends BaseDO {


    /**
     * 教练id
     */
    private String coachId;


    /**
     * 会员id
     */
    private String memberId;

    /**
     * 关联合同id
     */
    private String contractInfoId;


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