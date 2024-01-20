package com.chunhui.web.service;

import com.chunhui.web.constants.jljs.JljsContractStatusEnum;
import com.chunhui.web.dao.JljsContractInfoDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.JljsContractInfo;
import com.chunhui.web.pojo.query.JljsContractInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsContractInfoService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsContractInfoDao jljsContractInfoDao;

    public Result<PageResult<JljsContractInfoOutVO>> pageList(JljsContractInfoQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsContractInfoDao.pageListByQurey(query), commonConvert::toJljsContractInfoOutList));
    }

    public Result<JljsContractInfoOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsContractInfoListOut(jljsContractInfoDao.getById(id)));
    }

    public Result<String> save(JljsContractInfoSaveVO saveVO) {
        JljsContractInfo jljsContractInfo = commonConvert.toJljsContractInfo(saveVO);
        jljsContractInfo.setContractStatus(JljsContractStatusEnum.daikaika.getCode());
        jljsContractInfoDao.save(jljsContractInfo);
        return ResultGenerator.success();
    }

    public Result<String> update(JljsContractInfoUpdateVO updateVO) {
        jljsContractInfoDao.updateById(commonConvert.updatetoJljsContractInfo(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        jljsContractInfoDao.removeById(id);
        return ResultGenerator.success();
    }

}