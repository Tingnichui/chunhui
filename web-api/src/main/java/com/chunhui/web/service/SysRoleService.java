package com.chunhui.web.service;

import com.chunhui.web.dao.SysRoleDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.SysRoleQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysRoleService {
private final CommonConvert commonConvert = CommonConvert.INSTANCE;
@Resource
private SysRoleDao sysRoleDao;

public Result
<PageResult
<SysRoleOutVO>> pageList(SysRoleQuery query) {
    return ResultGenerator.success(PageUtil.pageResult(sysRoleDao.pageListByQurey(query),
    commonConvert::toSysRoleOutList));
    }

    public Result
    <SysRoleOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysRoleListOut(sysRoleDao.getById(id)));
        }

        public Result
        <String> save(SysRoleSaveVO saveVO) {
            sysRoleDao.save(commonConvert.toSysRole(saveVO));
            return ResultGenerator.success();
            }

            public Result
            <String> update(SysRoleUpdateVO updateVO) {
                sysRoleDao.updateById(commonConvert.updatetoSysRole(updateVO));
                return ResultGenerator.success();
                }

                public Result
                <String> delete(String id) {
                    sysRoleDao.removeById(id);
                    return ResultGenerator.success();
                    }

                    }