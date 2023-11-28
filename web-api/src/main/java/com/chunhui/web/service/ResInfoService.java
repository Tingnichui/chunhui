package com.chunhui.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.chunhui.web.dao.ResFileDao;
import com.chunhui.web.dao.ResInfoDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.ResFile;
import com.chunhui.web.pojo.po.ResInfo;
import com.chunhui.web.pojo.query.ResInfoQuery;
import com.chunhui.web.pojo.vo.ResInfoOutVO;
import com.chunhui.web.pojo.vo.ResInfoSaveVO;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * res_info 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@Service
public class ResInfoService {

    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private ResInfoDao resInfoDao;
    @Resource
    private ResFileDao resFileDao;

    @Resource
    private SysFileService sysFileService;

    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(ResInfoSaveVO resInfo) {
        ResInfo po = commonConvert.toPo(resInfo);
        resInfoDao.save(po);
        for (String fileId : resInfo.getFileList()) {
            ResFile resFile = new ResFile();
            resFile.setResId(po.getId());
            resFile.setFileId(fileId);
            resFileDao.save(resFile);
        }
        return ResultGenerator.success();
    }

    public Result<IPage<ResInfo>> pageList(ResInfoQuery query) {
        return ResultGenerator.success(resInfoDao.pageListByQurey(query));
    }

    public Result<ResInfoOutVO> getResInfoById(String id) {
        ResInfo info = resInfoDao.getById(id);
        if (null == info) {
            return ResultGenerator.fail("资源不存在");
        }
        ResInfoOutVO out = commonConvert.toOut(info);
        List<String> fileIds = SimpleQuery.list(Wrappers.lambdaQuery(ResFile.class).eq(ResFile::getResId, info.getId()), ResFile::getFileId);
        out.setFileList(sysFileService.listByIds(fileIds));

        return ResultGenerator.success(out);
    }

    public Result<ResInfoOutVO> deleteById(String id) {
        resInfoDao.removeById(id);
        return ResultGenerator.success();
    }
}