package com.chunhui.web.pojo.vo;

import lombok.Data;

@Data
public class SysPermissionOutVO {


    private String id;


    /**
    * 权限名称
    */
    private String name;


    /**
    * 权限类型
    */
    private String permissionType;


}
