package com.chunhui.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {


    NO_LOGIN(1001, "未登录"),
    ;

    private final Integer code;
    private final String message;

}
