package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.${tableBigHump}Mapper;
import com.chunhui.web.pojo.po.${tableBigHump};
import com.chunhui.web.pojo.query.${tableBigHump}Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class ${tableBigHump}Dao extends BaseDao
<${tableBigHump}Mapper, ${tableBigHump}> {

@Resource
private ${tableBigHump}Mapper ${tableHump}Mapper;

public IPage<${tableBigHump}> pageListByQurey(${tableBigHump}Query query) {
LambdaQueryWrapper<${tableBigHump}> queryWrapper = Wrappers.lambdaQuery();
<#list fields as field>
    queryWrapper.like(StringUtils.isNotBlank(query.get${field.nameBigHump}()), ${tableBigHump}::get${field.nameBigHump}, query.get${field.nameBigHump}());
</#list>
return super.pageList(query, queryWrapper);
}

}