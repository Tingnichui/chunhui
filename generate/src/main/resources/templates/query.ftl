package com.chunhui.web.pojo.query;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ${tableBigHump}Query extends QueryBase {

    private String id;

<#-- 属性遍历 -->
<#list fields as field>

    /**
    * ${field.comment}
    */
    private ${field.javaType} ${field.nameHump};

</#list>

}
