package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class SysMenuUpdateVO {


    @NotBlank(message = "主键id不能为空")
    private String id;


    /**
     * 菜单名称
     */
    private String name;


    /**
     * 菜单地址
     */
    private String url;


    /**
     * 图标
     */
    private String icon;


}
