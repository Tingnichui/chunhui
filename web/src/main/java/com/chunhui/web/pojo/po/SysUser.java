package com.chunhui.web.pojo.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {

    private int id;
    private String userName;
    private String password;
}
