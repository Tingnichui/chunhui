package com.chunhui.web.util;

import com.chunhui.web.pojo.vo.Result;

public class ResultGeneretor {

    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.setCode(Result.FAIL_CODE);
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T o) {
        Result<T> result = new Result<>();
        result.setCode(Result.FAIL_CODE);
        result.setMessage("操作成功");
        result.setData(o);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }
}
