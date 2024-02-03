package com.chunhui.web.constants.system;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PermissionType {

    MENU("1", "菜单"),
    ;


    private final String code;
    private final String mean;
}
