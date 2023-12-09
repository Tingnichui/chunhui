package com.chunhui.web.service;

import com.chunhui.web.dao.SysUserRoleDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.SysUserRoleQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysUserRoleService {
private final CommonConvert commonConvert = CommonConvert.INSTANCE;
@Resource
private SysUserRoleDao sysUserRoleDao;

public Result
<PageResult
<SysUserRoleOutVO>> pageList(SysUserRoleQuery query) {
    return ResultGenerator.success(PageUtil.pageResult(sysUserRoleDao.pageListByQurey(query),
    commonConvert::toSysUserRoleOutList));
    }

    public Result
    <SysUserRoleOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysUserRoleListOut(sysUserRoleDao.getById(id)));
        }

        public Result
        <String> save(SysUserRoleSaveVO saveVO) {
            sysUserRoleDao.save(commonConvert.toSysUserRole(saveVO));
            return ResultGenerator.success();
            }

            public Result
            <String> update(SysUserRoleUpdateVO updateVO) {
                sysUserRoleDao.updateById(commonConvert.updatetoSysUserRole(updateVO));
                return ResultGenerator.success();
                }

                public Result
                <String> delete(String id) {
                    sysUserRoleDao.removeById(id);
                    return ResultGenerator.success();
                    }

                    }