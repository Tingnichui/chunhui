package com.chunhui.web.pojo.vo;

import lombok.Data;

@Data
public class SysRolePermissionOutVO {


    private String id;


    /**
    * 角色id
    */
    private String roleId;


    /**
    * 权限id
    */
    private String permissionId;


}
