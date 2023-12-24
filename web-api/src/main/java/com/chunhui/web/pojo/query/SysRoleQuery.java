package com.chunhui.web.pojo.query;

import lombok.Data;


@Data
public class SysRoleQuery extends QueryBase {

    private String id;


    /**
     * 角色名称
     */
    private String roleName;


}
