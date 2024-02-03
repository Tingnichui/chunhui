package com.chunhui.web.controller.system;

import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.pojo.vo.AliyunOssUploadAccess;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.pojo.vo.SysFileSaveVO;
import com.chunhui.web.service.system.SysFileService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

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

    @RequestMapping("/getDownLoadAccess")
    public Result<String> getDownLoadAccess(@NotBlank(message = "id不能为空") String id) {
        return sysFileService.getDownLoadAccess(id);
    }

}