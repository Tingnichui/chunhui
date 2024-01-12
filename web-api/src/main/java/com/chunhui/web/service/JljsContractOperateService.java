package com.chunhui.web.service;

import com.chunhui.web.dao.JljsContractOperateDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.JljsContractOperateQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsContractOperateService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsContractOperateDao jljsContractOperateDao;

    public Result
            <PageResult
                    <JljsContractOperateOutVO>> pageList(JljsContractOperateQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsContractOperateDao.pageListByQurey(query),
                commonConvert::toJljsContractOperateOutList));
    }

    public Result
            <JljsContractOperateOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsContractOperateListOut(jljsContractOperateDao.getById(id)));
    }

    public Result
            <String> save(JljsContractOperateSaveVO saveVO) {
        jljsContractOperateDao.save(commonConvert.toJljsContractOperate(saveVO));
        return ResultGenerator.success();
    }

    public Result
            <String> update(JljsContractOperateUpdateVO updateVO) {
        jljsContractOperateDao.updateById(commonConvert.updatetoJljsContractOperate(updateVO));
        return ResultGenerator.success();
    }

    public Result
            <String> delete(String id) {
        jljsContractOperateDao.removeById(id);
        return ResultGenerator.success();
    }

}