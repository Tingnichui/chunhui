package com.chunhui.web.controller;


import com.chunhui.web.pojo.query.${tableBigHump}Query;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.${tableBigHump}Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/${tableHump}")
public class ${tableBigHump}Controller {

@Resource
private ${tableBigHump}Service ${tableHump}Service;

@RequestMapping("/pageList")
public Result<PageResult<${tableBigHump}OutVO>> regist(@RequestBody ${tableBigHump}Query query) {
    return ${tableHump}Service.pageList(query);
    }

@RequestMapping("/detail")
public Result<${tableBigHump}OutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
return ${tableHump}Service.detail(id);
}

@RequestMapping("/save")
public Result<String> save(@RequestBody ${tableBigHump}SaveVO saveVO) {
return ${tableHump}Service.save(saveVO);
}


@RequestMapping("/update")
public Result<String> update(@RequestBody ${tableBigHump}UpdateVO updateVO) {
return ${tableHump}Service.update(updateVO);
}

@RequestMapping("/delete")
public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
return ${tableHump}Service.delete(id);
}


}
