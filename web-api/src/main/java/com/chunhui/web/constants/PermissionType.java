package com.chunhui.web.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PermissionType {

    MENU("1", "菜单"),
    ;


    private final String type;
    private final String mean;
}
