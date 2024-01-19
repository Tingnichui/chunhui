package com.chunhui.web.constants.jljs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JljsContractOperateTypeEnum {

    kaika("1", "开卡"),
    zanting("2", "暂停"),
    tuike("3", "退课"),
    bujiao("4", "补缴"),
    ;


    private final String code;
    private final String mean;

}
