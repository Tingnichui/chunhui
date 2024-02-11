package com.chunhui.web.constants.system;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UrlAuthStatusEnum {
    NO("0", "不鉴权"),
    YES("1", "鉴权"),
    ;

    private final String key;
    private final String mean;
}
