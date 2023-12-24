package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class SysRoleUpdateVO {


    @NotBlank(message = "主键id不能为空")
    private String id;


    /**
     * 角色名称
     */
    private String roleName;


}
