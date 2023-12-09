package com.chunhui.web.pojo.query;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SysUserQuery extends QueryBase {

    private String id;


    /**
    * 用户名
    */
    private String userName;


    /**
    * 昵称
    */
    private String nickName;


    /**
    * 手机号
    */
    private String phoneNum;


    /**
    * 密码
    */
    private String password;


}
