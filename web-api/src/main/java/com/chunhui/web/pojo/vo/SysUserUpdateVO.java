package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class SysUserUpdateVO {


    @NotBlank(message = "主键id不能为空")
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
