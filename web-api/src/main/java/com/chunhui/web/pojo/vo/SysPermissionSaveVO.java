package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SysPermissionSaveVO {


    /**
    * 权限名称
    */
    private String name;


    /**
    * 权限类型
    */
    private String permissionType;


}
