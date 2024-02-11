package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
* 系统菜单
*/
@Data
@TableName("sys_url")
public class SysUrl extends BaseDO {



    /**
    * 父id
    */
    private String parentId;


    /**
    * 接口地址
    */
    private String urlPath;


    /**
    * 接口名称
    */
    private String urlName;


    /**
    * 接口描述
    */
    private String urlDesc;


    /**
    * 是否鉴权；0不鉴权 1鉴权
    */
    private String urlAuthStatus;


    /**
    * 接口类名
    */
    private String urlClassName;


    /**
    * 接口方法名称
    */
    private String urlMethodName;


}