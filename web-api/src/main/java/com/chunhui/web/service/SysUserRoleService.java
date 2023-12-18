package com.chunhui.web.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.dao.SysUserRoleDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.SysUserRole;
import com.chunhui.web.pojo.query.SysUserRoleQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SysUserRoleService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    public Result<PageResult<SysUserRoleOutVO>> pageList(SysUserRoleQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(sysUserRoleDao.pageListByQurey(query), commonConvert::toSysUserRoleOutList));
    }

    public Result<SysUserRoleOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysUserRoleListOut(sysUserRoleDao.getById(id)));
    }


    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(SysUserRoleSaveVO saveVO) {
        List<String> roleIdList = saveVO.getRoleIdList();
        if (null != roleIdList && !roleIdList.isEmpty()) {
            sysUserRoleDao.remove(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, saveVO.getUserId()).notIn(SysUserRole::getRoleId, roleIdList));
            for (String roleId : roleIdList) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(saveVO.getUserId());
                sysUserRole.setRoleId(roleId);
                sysUserRoleDao.save(sysUserRole);
            }
        } else {
            sysUserRoleDao.remove(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, saveVO.getUserId()));
        }
        return ResultGenerator.success();
    }

    public Result<String> update(SysUserRoleUpdateVO updateVO) {
        sysUserRoleDao.updateById(commonConvert.updatetoSysUserRole(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        sysUserRoleDao.removeById(id);
        return ResultGenerator.success();
    }

}