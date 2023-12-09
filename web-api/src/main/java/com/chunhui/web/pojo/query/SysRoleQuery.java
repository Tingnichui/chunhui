package com.chunhui.web.pojo.query;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SysRoleQuery extends QueryBase {

    private String id;


    /**
    * 角色名称
    */
    private String roleName;


}
