package com.chunhui.web.exception;


public class BusinessException extends Exception{

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
    }
}
