package com.chunhui.web.controller.jljs;


import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.pojo.query.JljsClassRecordQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.service.jljs.JljsClassRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/jljsClassRecord")
public class JljsClassRecordController {

    @Resource
    private JljsClassRecordService jljsClassRecordService;

    @RequestMapping("/pageList")
    public Result<PageResult<JljsClassRecordOutVO>> pageList(@RequestBody JljsClassRecordQuery query) {
        return jljsClassRecordService.pageList(query);
    }

    @RequestMapping("/detail")
    public Result<JljsClassRecordOutVO> detail(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsClassRecordService.detail(id);
    }

    @RequestMapping("/save")
    public Result<String> save(@Validated @RequestBody JljsClassRecordSaveVO saveVO) throws BusinessException {
        return jljsClassRecordService.save(saveVO);
    }

    @RequestMapping("/update")
    public Result<String> update(@Validated @RequestBody JljsClassRecordUpdateVO updateVO) throws BusinessException {
        return jljsClassRecordService.update(updateVO);
    }

    @RequestMapping("/delete")
    public Result<String> delete(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return jljsClassRecordService.delete(id);
    }


}
