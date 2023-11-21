package com.chunhui.web.exception;


import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.util.ResultGeneretor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("全局异常处理", e);
        return ResultGeneretor.fail(e.getMessage());
    }

}
