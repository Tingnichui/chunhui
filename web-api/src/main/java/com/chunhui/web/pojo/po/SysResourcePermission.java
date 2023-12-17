package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 系统资源权限关联关系
 */
@Data
@TableName("sys_resource_permission")
public class SysResourcePermission extends BaseDO {


    /**
     * 权限id
     */
    private String permissionId;


    /**
     * 资源id
     */
    private String resourceId;


}