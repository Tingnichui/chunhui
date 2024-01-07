package com.chunhui.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {


    NO_LOGIN("1001", "未登录"),
    NO_USER("1002", "用户不存在"),
    SAVE_FAIL("1003", "数据库保存失败"),
    PARAMS_INVALID("1004", "参数校验失败"),
    METHOD_NOT_ALLOWED("1005", "请求方法不被允许"),
    BAD_REQUEST("1006", "请求方式错误"),
    NOT_FOUND("1007", "接口不存在"),
    NOT_CONFIGURE_MENU("1008", "未配置菜单权限"),
    ;

    private final String code;
    private final String message;

}
