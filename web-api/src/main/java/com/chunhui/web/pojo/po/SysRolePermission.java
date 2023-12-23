package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 角色权限
 */
@Data
@TableName("sys_role_permission")
public class SysRolePermission extends BaseDO {


    /**
     * 角色id
     */
    private String roleId;


    /**
     * 权限id
     */
    private String permissionId;


}