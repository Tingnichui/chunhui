package com.chunhui.web.pojo.query;

import lombok.Data;


@Data
public class SysRolePermissionQuery extends QueryBase {

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
