package com.chunhui.web.pojo.vo;

import lombok.Data;


@Data
public class SysUserSaveVO {


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
