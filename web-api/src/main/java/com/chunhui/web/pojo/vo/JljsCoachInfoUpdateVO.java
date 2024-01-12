package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class JljsCoachInfoUpdateVO {


    @NotBlank(message = "主键id不能为空")
    private String id;


    /**
     * 教练姓名
     */
    private String coachName;


    /**
     * 电话号码
     */
    private String coachPhoneNum;


}
