package com.chunhui.web.pojo.vo;

import lombok.Data;

@Data
public class Result<T> {

    public static final String SUCCESS_CODE = "0000";
    public static final String FAIL_CODE = "9999";

    private String code;
    private String message;
    private T data;

    public boolean success() {
        return SUCCESS_CODE.equals(this.code);
    }

}
