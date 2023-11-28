package com.chunhui.web.controller;

import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.pojo.vo.AliyunOssUploadAccess;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.pojo.vo.SysFileSaveVO;
import com.chunhui.web.service.SysFileService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * sys_file 文件表;
 *
 * @author : genghui
 * @date : 2023-11-25
 */
@Validated
@RestController
@RequestMapping("/sysFile")
public class SysFileController {
    @Resource
    private SysFileService sysFileService;

    @RequestMapping("/getUploadAccess")
    public Result<AliyunOssUploadAccess> regist(@Validated @RequestBody SysFileSaveVO sysFile) throws BusinessException {
        return sysFileService.saveAndGetAccess(sysFile);
    }

}