package com.chunhui.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chunhui.web.dao.ResInfoDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.ResInfo;
import com.chunhui.web.pojo.query.ResInfoQuery;
import com.chunhui.web.pojo.vo.ResInfoVO;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public Result<String> save(ResInfoVO resInfo) {
        resInfoDao.save(commonConvert.toPo(resInfo));
        return ResultGenerator.success();
    }

    public Result<IPage<ResInfo>> pageList(ResInfoQuery query) {
        return ResultGenerator.success(resInfoDao.pageListByQurey(query));
    }
}