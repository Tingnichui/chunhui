package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 加励健身-教练信息
 */
@Data
@TableName("jljs_coach_info")
public class JljsCoachInfo extends BaseDO {


    /**
     * 教练姓名
     */
    private String coachName;


    /**
     * 电话号码
     */
    private String coachPhoneNum;


}