package com.chunhui.web.service;

import com.chunhui.web.dao.SysPermissionDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.SysPermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysPermissionService {
private final CommonConvert commonConvert = CommonConvert.INSTANCE;
@Resource
private SysPermissionDao sysPermissionDao;

public Result
<PageResult
<SysPermissionOutVO>> pageList(SysPermissionQuery query) {
    return ResultGenerator.success(PageUtil.pageResult(sysPermissionDao.pageListByQurey(query),
    commonConvert::toSysPermissionOutList));
    }

    public Result
    <SysPermissionOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysPermissionListOut(sysPermissionDao.getById(id)));
        }

        public Result
        <String> save(SysPermissionSaveVO saveVO) {
            sysPermissionDao.save(commonConvert.toSysPermission(saveVO));
            return ResultGenerator.success();
            }

            public Result
            <String> update(SysPermissionUpdateVO updateVO) {
                sysPermissionDao.updateById(commonConvert.updatetoSysPermission(updateVO));
                return ResultGenerator.success();
                }

                public Result
                <String> delete(String id) {
                    sysPermissionDao.removeById(id);
                    return ResultGenerator.success();
                    }

                    }