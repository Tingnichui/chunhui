package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
* 系统菜单权限
*/
@Data
@TableName("sys_menu_permission")
public class SysMenuPermission extends BaseDO {



    /**
    * 菜单id
    */
    private String menuId;


    /**
    * 权限id
    */
    private String permissionId;


}