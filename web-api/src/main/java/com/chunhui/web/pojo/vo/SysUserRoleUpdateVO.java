package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class SysUserRoleUpdateVO {


    @NotBlank(message = "主键id不能为空")
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
