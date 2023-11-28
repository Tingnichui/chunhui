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
import com.chunhui.web.pojo.vo.PageResult;
import com.chunhui.web.pojo.vo.ResInfoOutVO;
import com.chunhui.web.pojo.vo.ResInfoSaveVO;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    public Result<String> save(ResInfoSaveVO invo) {
        // 新增
        ResInfo resInfo = commonConvert.toPo(invo);
        resInfoDao.save(resInfo);
        for (String fileId : invo.getFileList()) {
            ResFile resFile = new ResFile();
            resFile.setResId(resInfo.getId());
            resFile.setFileId(fileId);
            resFileDao.save(resFile);
        }
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> update(ResInfoSaveVO invo) {
        ResInfo resInfo = commonConvert.toPo(invo);
        resInfoDao.updateById(resInfo);
        // 删除解除关系的
        List<String> fileList = invo.getFileList();
        List<String> list = SimpleQuery.list(Wrappers.lambdaQuery(ResFile.class).eq(ResFile::getResId, resInfo.getId()), ResFile::getFileId);
        List<String> delIds = list.stream().filter(v -> !fileList.contains(v)).collect(Collectors.toList());
        if (!delIds.isEmpty()) {
            for (String delId : delIds) {
                resFileDao.remove(Wrappers.lambdaQuery(ResFile.class).eq(ResFile::getResId, resInfo.getId()).eq(ResFile::getFileId, delId));
            }
        }
        for (String fileId : fileList) {
            long count = resFileDao.count(Wrappers.lambdaQuery(ResFile.class).eq(ResFile::getResId, resInfo.getId()).eq(ResFile::getFileId, fileId));
            if (count > 0) {
                continue;
            }
            ResFile resFile = new ResFile();
            resFile.setResId(resInfo.getId());
            resFile.setFileId(fileId);
            resFileDao.save(resFile);
        }
        return ResultGenerator.success();
    }

    public Result<PageResult<ResInfoOutVO>> pageList(ResInfoQuery query) {
        IPage<ResInfo> resInfoIPage = resInfoDao.pageListByQurey(query);
        PageResult<ResInfoOutVO> page = new PageResult<>();
        page.setCurrent(resInfoIPage.getCurrent());
        page.setPages(resInfoIPage.getPages());
        page.setSize(resInfoIPage.getSize());
        page.setTotal(resInfoIPage.getTotal());
        page.setRecords(commonConvert.toResOutList(resInfoIPage.getRecords()));
        return ResultGenerator.success(page);
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