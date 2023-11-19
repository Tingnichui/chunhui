package com.chunhui.web.pojo.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表基础字段
 */
@Getter
@Setter
public class BaseDO implements Serializable {

    private String id;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    private Boolean delFlag;
}
