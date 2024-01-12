package com.chunhui.web.service;

import com.chunhui.web.dao.JljsMemberOrderDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.JljsMemberOrderQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsMemberOrderService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private JljsMemberOrderDao jljsMemberOrderDao;

    public Result
            <PageResult
                    <JljsMemberOrderOutVO>> pageList(JljsMemberOrderQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(jljsMemberOrderDao.pageListByQurey(query),
                commonConvert::toJljsMemberOrderOutList));
    }

    public Result
            <JljsMemberOrderOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsMemberOrderListOut(jljsMemberOrderDao.getById(id)));
    }

    public Result
            <String> save(JljsMemberOrderSaveVO saveVO) {
        jljsMemberOrderDao.save(commonConvert.toJljsMemberOrder(saveVO));
        return ResultGenerator.success();
    }

    public Result
            <String> update(JljsMemberOrderUpdateVO updateVO) {
        jljsMemberOrderDao.updateById(commonConvert.updatetoJljsMemberOrder(updateVO));
        return ResultGenerator.success();
    }

    public Result
            <String> delete(String id) {
        jljsMemberOrderDao.removeById(id);
        return ResultGenerator.success();
    }

}