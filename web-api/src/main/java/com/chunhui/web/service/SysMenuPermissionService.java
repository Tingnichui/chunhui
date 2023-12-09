package com.chunhui.web.service;

import com.chunhui.web.dao.SysMenuPermissionDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.SysMenuPermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysMenuPermissionService {
private final CommonConvert commonConvert = CommonConvert.INSTANCE;
@Resource
private SysMenuPermissionDao sysMenuPermissionDao;

public Result
<PageResult
<SysMenuPermissionOutVO>> pageList(SysMenuPermissionQuery query) {
    return ResultGenerator.success(PageUtil.pageResult(sysMenuPermissionDao.pageListByQurey(query),
    commonConvert::toSysMenuPermissionOutList));
    }

    public Result
    <SysMenuPermissionOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysMenuPermissionListOut(sysMenuPermissionDao.getById(id)));
        }

        public Result
        <String> save(SysMenuPermissionSaveVO saveVO) {
            sysMenuPermissionDao.save(commonConvert.toSysMenuPermission(saveVO));
            return ResultGenerator.success();
            }

            public Result
            <String> update(SysMenuPermissionUpdateVO updateVO) {
                sysMenuPermissionDao.updateById(commonConvert.updatetoSysMenuPermission(updateVO));
                return ResultGenerator.success();
                }

                public Result
                <String> delete(String id) {
                    sysMenuPermissionDao.removeById(id);
                    return ResultGenerator.success();
                    }

                    }