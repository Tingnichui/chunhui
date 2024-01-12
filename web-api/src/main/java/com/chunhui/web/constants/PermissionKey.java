package com.chunhui.web.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PermissionKey {
    USER_HOME_MENU("user_home_menu", "用户首页菜单"),
    ;

    private final String key;
    private final String mean;
}
