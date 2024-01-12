package com.chunhui.web.service;

import com.chunhui.web.dao.JljsContractOperateRecordDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.JljsContractOperateRecordQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JljsContractOperateRecordService {
private final CommonConvert commonConvert = CommonConvert.INSTANCE;
@Resource
private JljsContractOperateRecordDao jljsContractOperateRecordDao;

public Result
<PageResult
<JljsContractOperateRecordOutVO>> pageList(JljsContractOperateRecordQuery query) {
    return ResultGenerator.success(PageUtil.pageResult(jljsContractOperateRecordDao.pageListByQurey(query),
    commonConvert::toJljsContractOperateRecordOutList));
    }

    public Result
    <JljsContractOperateRecordOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toJljsContractOperateRecordListOut(jljsContractOperateRecordDao.getById(id)));
        }

        public Result
        <String> save(JljsContractOperateRecordSaveVO saveVO) {
            jljsContractOperateRecordDao.save(commonConvert.toJljsContractOperateRecord(saveVO));
            return ResultGenerator.success();
            }

            public Result
            <String> update(JljsContractOperateRecordUpdateVO updateVO) {
                jljsContractOperateRecordDao.updateById(commonConvert.updatetoJljsContractOperateRecord(updateVO));
                return ResultGenerator.success();
                }

                public Result
                <String> delete(String id) {
                    jljsContractOperateRecordDao.removeById(id);
                    return ResultGenerator.success();
                    }

                    }