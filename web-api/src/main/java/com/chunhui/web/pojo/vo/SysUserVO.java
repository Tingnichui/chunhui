package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserVO implements Serializable {

    private String id;
    private String userName;
    private String password;
}
