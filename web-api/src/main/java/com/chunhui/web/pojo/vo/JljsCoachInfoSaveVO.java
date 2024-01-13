package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class JljsCoachInfoSaveVO {


    /**
     * 教练姓名
     */
    @NotBlank(message = "教练姓名不能为空")
    private String coachName;


    /**
     * 电话号码
     */
    private String coachPhoneNum;


}
