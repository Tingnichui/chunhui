package com.chunhui.web.pojo.query;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class SysMenuQuery extends QueryBase {

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
