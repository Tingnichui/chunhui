package com.chunhui.web.pojo.query;

import lombok.Data;


@Data
public class SysPermissionQuery extends QueryBase {

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
