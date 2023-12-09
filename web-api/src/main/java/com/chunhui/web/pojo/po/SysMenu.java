package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
* 系统菜单
*/
@Data
@TableName("sys_menu")
public class SysMenu extends BaseDO {



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