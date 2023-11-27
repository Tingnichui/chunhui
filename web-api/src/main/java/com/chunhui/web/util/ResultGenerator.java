package com.chunhui.web.util;

import com.chunhui.web.exception.ExceptionEnum;
import com.chunhui.web.pojo.vo.Result;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ResultGenerator {

    public static <T> Result<T> fail(String code, List<String> errorList) {
        return fail(Result.FAIL_CODE, StringUtils.join(errorList, "\r\n "));
    }

    public static <T> Result<T> fail(String msg) {
        return fail(Result.FAIL_CODE, msg);
    }

    public static <T> Result<T> fail(ExceptionEnum exceptionEnum) {
        return fail(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public static <T> Result<T> fail(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T o) {
        Result<T> result = new Result<>();
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage("操作成功");
        result.setData(o);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }
}
