package com.chunhui.web.service.system;

import com.chunhui.web.dao.SysFileDao;
import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.SysFile;
import com.chunhui.web.pojo.vo.AliyunOssUploadAccess;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.pojo.vo.SysFileOutVO;
import com.chunhui.web.pojo.vo.SysFileSaveVO;
import com.chunhui.web.util.AliyunOssUtil;
import com.chunhui.web.util.ResultGenerator;
import com.chunhui.web.util.ServerletUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * sys_file 文件表;
 *
 * @author : genghui
 * @date : 2023-11-25
 */
@Service
public class SysFileService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private AliyunOssUtil aliyunOssUtil;
    @Resource
    private SysFileDao sysFileDao;

    public Result<AliyunOssUploadAccess> getUploadAccess() throws BusinessException {
        return ResultGenerator.success(aliyunOssUtil.getUploadAccess("user/" + ServerletUtil.getUserInfo().getId() + "/", null));
    }

    public Result<AliyunOssUploadAccess> saveAndGetAccess(SysFileSaveVO invo) throws BusinessException {
        // 获取oss上传权限
        String dir = "user/" + ServerletUtil.getUserInfo().getId() + "/";
        String fileName = System.currentTimeMillis() / 1000 + "-" + invo.getFileName();
        AliyunOssUploadAccess uploadAccess = aliyunOssUtil.getUploadAccess(dir, fileName);
        // 填充sysfile 保存到数据库
        SysFile sysFile = commonConvert.toPo(invo);
        sysFile.setFilePath(dir + fileName);
        sysFile.setStorageServices("oss");
        sysFile.setUrl(uploadAccess.getHost() + "/" + sysFile.getFilePath());
        sysFileDao.save(sysFile);
        uploadAccess.setId(sysFile.getId());
        return ResultGenerator.success(uploadAccess);
    }

    public List<SysFileOutVO> listByIds(List<String> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return commonConvert.toOutList(sysFileDao.listByIds(ids));
    }

    public Result<String> getDownLoadAccess(String id) {
        SysFile sysFile = sysFileDao.getById(id);
        if (null == sysFile) {
            return ResultGenerator.fail("文件不存在");
        }
        String filePath = sysFile.getFilePath();
        if (StringUtils.isBlank(filePath)) {
            return ResultGenerator.fail("文件保存路径为空");
        }
        return ResultGenerator.success(aliyunOssUtil.getDownLoadAccess(filePath));
    }
}