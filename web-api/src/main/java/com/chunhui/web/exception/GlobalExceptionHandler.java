package com.chunhui.web.exception;


import cn.hutool.core.collection.CollectionUtil;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.util.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("全局异常处理", e);
        return ResultGenerator.fail(e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result<String> businessExceptionHandler(BusinessException e) {
        return ResultGenerator.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理 json 请求体调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> jsonParamsException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorList = CollectionUtil.newArrayList();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            String msg = String.format("%s%s；", fieldError.getField(), fieldError.getDefaultMessage());
//            errorList.add(fieldError.getDefaultMessage());
            return ResultGenerator.fail(ExceptionEnum.PARAMS_INVALID.getCode(), fieldError.getDefaultMessage());
        }
        return ResultGenerator.fail(ExceptionEnum.PARAMS_INVALID.getCode(), errorList);
    }


    /**
     * 处理单个参数校验失败抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> ParamsException(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            return ResultGenerator.fail(ExceptionEnum.PARAMS_INVALID.getCode(), violation.getMessage());
        }
        return ResultGenerator.fail(ExceptionEnum.PARAMS_INVALID.getCode(), "未知异常");
    }

    /**
     * @param e
     * @return 处理 form data方式调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public Result<String> formDaraParamsException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getField() + o.getDefaultMessage())
                .collect(Collectors.toList());
        return ResultGenerator.fail(ExceptionEnum.PARAMS_INVALID.getCode(), collect);
    }

    /**
     * 请求方法不被允许异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResultGenerator.fail(ExceptionEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * @param e
     * @return Content-Type/Accept 异常
     * application/json
     * application/x-www-form-urlencoded
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<String> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return ResultGenerator.fail(ExceptionEnum.BAD_REQUEST);
    }

    /**
     * handlerMapping  接口不存在跑出异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<String> noHandlerFoundException(NoHandlerFoundException e) {
        return ResultGenerator.fail(ExceptionEnum.NOT_FOUND.getCode(), e.getMessage());
    }

}
