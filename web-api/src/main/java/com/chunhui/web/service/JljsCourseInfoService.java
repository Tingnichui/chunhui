package com.chunhui.web.service;

import com.chunhui.web.dao.JljsCourseInfoDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.JljsCourseInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsCourseInfoService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsCourseInfoDao jljsCourseInfoDao;

    public Result
            <PageResult
                    <JljsCourseInfoOutVO>> pageList(JljsCourseInfoQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsCourseInfoDao.pageListByQurey(query),
                commonConvert::toJljsCourseInfoOutList));
    }

    public Result
            <JljsCourseInfoOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsCourseInfoListOut(jljsCourseInfoDao.getById(id)));
    }

    public Result
            <String> save(JljsCourseInfoSaveVO saveVO) {
        jljsCourseInfoDao.save(commonConvert.toJljsCourseInfo(saveVO));
        return ResultGenerator.success();
    }

    public Result
            <String> update(JljsCourseInfoUpdateVO updateVO) {
        jljsCourseInfoDao.updateById(commonConvert.updatetoJljsCourseInfo(updateVO));
        return ResultGenerator.success();
    }

    public Result
            <String> delete(String id) {
        jljsCourseInfoDao.removeById(id);
        return ResultGenerator.success();
    }

}