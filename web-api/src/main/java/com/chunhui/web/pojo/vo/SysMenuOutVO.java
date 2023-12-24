package com.chunhui.web.pojo.vo;

import lombok.Data;

@Data
public class SysMenuOutVO {


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
