package com.chunhui.pojo;

import lombok.Data;

@Data
public class Field {

    /**
     * 字段名;
     */
    private String name;
    /**
     * 字段名小驼峰;
     */
    private String nameHump;
    /**
     * 字段名大驼峰;
     */
    private String nameBigHump;
    /**
     * 中文名;
     */
    private String nameCn;
    /**
     * 字段类型;
     */
    private String type;
    /**
     * java类型;
     */
    private String javaType;
    /**
     * 注释;
     */
    private String comment;
    /**
     * 是否可为空;
     */
    private Boolean nullAble;
    /**
     * 字符串长度;
     */
    private Integer length;
    /**
     * 是否是枚举;
     */
    private Boolean enums;
    /**
     * 枚举常量 COURSE_LEVEL;
     */
    private String enumsConst;
}
