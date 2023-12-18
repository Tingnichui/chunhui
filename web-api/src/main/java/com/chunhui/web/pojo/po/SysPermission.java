package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 系统权限
 */
@Data
@TableName("sys_permission")
public class SysPermission extends BaseDO {


    /**
     * 权限名称
     */
    private String name;


    /**
     * 权限类型
     */
    private String permissionType;
    

    /**
     * 权限关键词
     */
    private String permissionKey;


}