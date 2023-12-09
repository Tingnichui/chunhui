package com.chunhui.web.pojo.query;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SysMenuPermissionQuery extends QueryBase {

    private String id;


    /**
    * 菜单id
    */
    private String menuId;


    /**
    * 权限id
    */
    private String permissionId;


}
