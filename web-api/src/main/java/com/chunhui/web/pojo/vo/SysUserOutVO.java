package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysUserOutVO {


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

    /**
     * 角色id列表
     */
    private List<String> roleIdList;

    /**
     * 权限key列表
     */
    private List<String> permList;


}
