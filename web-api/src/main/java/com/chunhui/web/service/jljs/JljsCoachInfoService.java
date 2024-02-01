package com.chunhui.web.service.jljs;

import com.chunhui.web.dao.JljsCoachInfoDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.JljsCoachInfoQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsCoachInfoService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsCoachInfoDao jljsCoachInfoDao;

    public Result<PageResult<JljsCoachInfoOutVO>> pageList(JljsCoachInfoQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsCoachInfoDao.pageListByQurey(query), commonConvert::toJljsCoachInfoOutList));
    }

    public Result<JljsCoachInfoOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsCoachInfoListOut(jljsCoachInfoDao.getById(id)));
    }

    public Result<String> save(JljsCoachInfoSaveVO saveVO) {
        jljsCoachInfoDao.save(commonConvert.toJljsCoachInfo(saveVO));
        return ResultGenerator.success();
    }

    public Result<String> update(JljsCoachInfoUpdateVO updateVO) {
        jljsCoachInfoDao.updateById(commonConvert.updatetoJljsCoachInfo(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        jljsCoachInfoDao.removeById(id);
        return ResultGenerator.success();
    }

}