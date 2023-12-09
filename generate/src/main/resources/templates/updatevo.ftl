package com.chunhui.web.pojo.vo;

import lombok.Data;
import javax.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;


@Data
public class ${tableBigHump}UpdateVO {


@NotBlank(message = "主键id不能为空")
private String id;

<#-- 属性遍历 -->
<#list fields as field>

    /**
    * ${field.comment}
    */
    private ${field.javaType} ${field.nameHump};

</#list>

}
