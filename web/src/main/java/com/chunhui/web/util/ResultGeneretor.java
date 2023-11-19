package com.chunhui.web.util;

import com.chunhui.web.pojo.vo.Result;

public class ResultGeneretor {

    public static Result fail(String msg) {
        Result result = new Result<>();
        result.setCode(Result.FAIL_CODE);
        result.setMessage(msg);
        return result;
    }

    public static Result<Object> success(Object o) {
        Result<Object> result = new Result<>();
        result.setCode(Result.FAIL_CODE);
        result.setMessage("操作成功");
        result.setData(o);
        return result;
    }
}
