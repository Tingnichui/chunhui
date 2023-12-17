package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.List;

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

    /**
     * 资源id
     */
    private List<String> resourceIdList;


}
