package com.chunhui.web.constants.jljs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JljsContractStatusEnum {

    weishengxiao("1", "未生效"),
    shiong("2", "使用中"),
    wancheng("3", "已完成"),
    zanting("4", "暂停"),
    zhongzhi("5", "终止"),
    ;


    private final String code;
    private final String mean;

}
