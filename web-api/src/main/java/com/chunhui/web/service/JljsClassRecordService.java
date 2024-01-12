package com.chunhui.web.service;

import com.chunhui.web.dao.JljsClassRecordDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.JljsClassRecordQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsClassRecordService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsClassRecordDao jljsClassRecordDao;

    public Result
            <PageResult
                    <JljsClassRecordOutVO>> pageList(JljsClassRecordQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsClassRecordDao.pageListByQurey(query),
                commonConvert::toJljsClassRecordOutList));
    }

    public Result
            <JljsClassRecordOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsClassRecordListOut(jljsClassRecordDao.getById(id)));
    }

    public Result
            <String> save(JljsClassRecordSaveVO saveVO) {
        jljsClassRecordDao.save(commonConvert.toJljsClassRecord(saveVO));
        return ResultGenerator.success();
    }

    public Result
            <String> update(JljsClassRecordUpdateVO updateVO) {
        jljsClassRecordDao.updateById(commonConvert.updatetoJljsClassRecord(updateVO));
        return ResultGenerator.success();
    }

    public Result
            <String> delete(String id) {
        jljsClassRecordDao.removeById(id);
        return ResultGenerator.success();
    }

}