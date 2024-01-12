package com.chunhui.web.pojo.vo;

import lombok.Data;


@Data
public class JljsMemberInfoSaveVO {


    /**
     * 会员姓名
     */
    private String memberName;


    /**
     * 会员性别;1 男；2女
     */
    private String memberGender;


    /**
     * 会员年龄
     */
    private String memberAge;


    /**
     * 手机号
     */
    private String memberPhoneNum;


}
