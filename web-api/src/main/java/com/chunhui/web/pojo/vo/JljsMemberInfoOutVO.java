package com.chunhui.web.pojo.vo;

import lombok.Data;

@Data
public class JljsMemberInfoOutVO {


    private String id;


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

    /**
     * 生日
     */
    private String birthDay;


}
