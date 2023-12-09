package com.chunhui.web.pojo.vo;

import lombok.Data;

@Data
public class ${tableBigHump}OutVO {


    private String id;

<#-- 属性遍历 -->
<#list fields as field>

    /**
    * ${field.comment}
    */
    private ${field.javaType} ${field.nameHump};

</#list>

}
