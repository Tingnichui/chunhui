package com.chunhui.web.pojo.query;

import lombok.Data;


@Data
public class JljsMemberInfoQuery extends QueryBase {

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


}
