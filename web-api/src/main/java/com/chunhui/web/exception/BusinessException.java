package com.chunhui.web.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends Exception {

    private String code;

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
