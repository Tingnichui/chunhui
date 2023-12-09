package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ${tableBigHump}SaveVO {

<#-- 属性遍历 -->
<#list fields as field>

    /**
    * ${field.comment}
    */
    private ${field.javaType} ${field.nameHump};

</#list>

}
