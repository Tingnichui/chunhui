package com.chunhui.web.pojo.query;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SysUserRoleQuery extends QueryBase {

    private String id;


    /**
    * 用户id
    */
    private String userId;


    /**
    * 角色id
    */
    private String roleId;


}
