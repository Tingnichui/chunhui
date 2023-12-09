package com.chunhui.web.service;

import com.chunhui.web.dao.SysRolePermissionDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.SysRolePermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysRolePermissionService {
private final CommonConvert commonConvert = CommonConvert.INSTANCE;
@Resource
private SysRolePermissionDao sysRolePermissionDao;

public Result
<PageResult
<SysRolePermissionOutVO>> pageList(SysRolePermissionQuery query) {
    return ResultGenerator.success(PageUtil.pageResult(sysRolePermissionDao.pageListByQurey(query),
    commonConvert::toSysRolePermissionOutList));
    }

    public Result
    <SysRolePermissionOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysRolePermissionListOut(sysRolePermissionDao.getById(id)));
        }

        public Result
        <String> save(SysRolePermissionSaveVO saveVO) {
            sysRolePermissionDao.save(commonConvert.toSysRolePermission(saveVO));
            return ResultGenerator.success();
            }

            public Result
            <String> update(SysRolePermissionUpdateVO updateVO) {
                sysRolePermissionDao.updateById(commonConvert.updatetoSysRolePermission(updateVO));
                return ResultGenerator.success();
                }

                public Result
                <String> delete(String id) {
                    sysRolePermissionDao.removeById(id);
                    return ResultGenerator.success();
                    }

                    }