package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
* ${tableComment}
*/
@Data
@TableName("${tableName}")
public class ${tableBigHump} extends BaseDO {


<#-- 属性遍历 -->
<#list fields as field>

    /**
    * ${field.comment}
    */
    private ${field.javaType} ${field.nameHump};

</#list>

}