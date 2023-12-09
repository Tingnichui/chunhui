package com.chunhui.pojo;

import lombok.Data;

import java.util.List;


@Data
public class TableInfo {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表名称 小驼峰
     */
    private String tableHump;


    /**
     * 表名称 大驼峰
     */
    private String tableBigHump;

    /**
     * 表名称 中线连接
     */
    private String tableKebab;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 字段
     */
    private List<Field> fields;
}
