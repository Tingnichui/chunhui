package com.chunhui.web.service;

import com.chunhui.web.dao.SysFileDao;
import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.pojo.po.SysFile;
import com.chunhui.web.pojo.vo.AliyunOssUploadAccess;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.util.AliyunOssUtil;
import com.chunhui.web.util.ResultGenerator;
import com.chunhui.web.util.ServerletUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * sys_file 文件表;
 *
 * @author : genghui
 * @date : 2023-11-25
 */
@Service
public class SysFileService {
    @Resource
    private AliyunOssUtil aliyunOssUtil;

    @Resource
    private SysFileDao sysFileDao;

    public Result<AliyunOssUploadAccess> getUploadAccess() throws BusinessException {
        return ResultGenerator.success(aliyunOssUtil.getUploadAccess("user/" + ServerletUtil.getUserInfo().getId() + "/", null));
    }

    public Result<AliyunOssUploadAccess> saveAndGetAccess(SysFile sysFile) throws BusinessException {
        // 获取oss上传权限
        String dir = "user/" + ServerletUtil.getUserInfo().getId() + "/";
        String fileName = System.currentTimeMillis() / 1000 + "-" + sysFile.getFileName();
        AliyunOssUploadAccess uploadAccess = aliyunOssUtil.getUploadAccess(dir, fileName);
        // 填充sysfile 保存到数据库
        sysFile.setFilePath(dir + fileName);
        sysFile.setStorageServices("oss");
        sysFile.setUrl(uploadAccess.getHost() + sysFile.getFilePath());
        sysFileDao.save(sysFile);
        uploadAccess.setId(sysFile.getId());
        return ResultGenerator.success(uploadAccess);

    }
}