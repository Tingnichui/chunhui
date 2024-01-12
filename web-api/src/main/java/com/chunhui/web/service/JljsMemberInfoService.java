package com.chunhui.web.service;

import com.chunhui.web.dao.JljsMemberInfoDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.JljsMemberInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsMemberInfoService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsMemberInfoDao jljsMemberInfoDao;

    public Result
            <PageResult
                    <JljsMemberInfoOutVO>> pageList(JljsMemberInfoQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsMemberInfoDao.pageListByQurey(query),
                commonConvert::toJljsMemberInfoOutList));
    }

    public Result
            <JljsMemberInfoOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsMemberInfoListOut(jljsMemberInfoDao.getById(id)));
    }

    public Result
            <String> save(JljsMemberInfoSaveVO saveVO) {
        jljsMemberInfoDao.save(commonConvert.toJljsMemberInfo(saveVO));
        return ResultGenerator.success();
    }

    public Result
            <String> update(JljsMemberInfoUpdateVO updateVO) {
        jljsMemberInfoDao.updateById(commonConvert.updatetoJljsMemberInfo(updateVO));
        return ResultGenerator.success();
    }

    public Result
            <String> delete(String id) {
        jljsMemberInfoDao.removeById(id);
        return ResultGenerator.success();
    }

}