package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class SysRolePermissionSaveVO {


    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空")
    private String roleId;


    /**
     * 权限id
     */
    private List<String> permissionIdList;


}
