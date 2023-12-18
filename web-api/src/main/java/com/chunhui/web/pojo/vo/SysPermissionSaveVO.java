package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class SysPermissionSaveVO {


    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不嫩为空")
    private String name;


    /**
     * 权限类型
     */
    @NotBlank(message = "权限类型不嫩为空")
    private String permissionType;

    /**
     * 权限关键词
     */
    @NotBlank(message = "权限关键词不嫩为空")
    private String permissionKey;

    /**
     * 资源id
     */
    private List<String> resourceIdList;


}
