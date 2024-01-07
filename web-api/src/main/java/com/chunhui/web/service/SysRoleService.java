package com.chunhui.web.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.chunhui.web.dao.SysRoleDao;
import com.chunhui.web.dao.SysRolePermissionDao;
import com.chunhui.web.dao.SysUserRoleDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.SysRole;
import com.chunhui.web.pojo.po.SysRolePermission;
import com.chunhui.web.pojo.po.SysUserRole;
import com.chunhui.web.pojo.query.SysRoleQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;


@Service
public class SysRoleService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    public Result<PageResult<SysRoleOutVO>> pageList(SysRoleQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(sysRoleDao.pageListByQurey(query), commonConvert::toSysRoleOutList));
    }

    public Result<SysRoleOutVO> detail(String id) {
        SysRoleOutVO sysRoleListOut = commonConvert.toSysRoleListOut(sysRoleDao.getById(id));
        List<String> permissonIdList = SimpleQuery.list(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getRoleId, id), SysRolePermission::getPermissionId);
        sysRoleListOut.setPermissonIdList(permissonIdList);
        return ResultGenerator.success(sysRoleListOut);
    }

    public Result<String> save(SysRoleSaveVO saveVO) {
        sysRoleDao.save(commonConvert.toSysRole(saveVO));
        return ResultGenerator.success();
    }

    public Result<String> update(SysRoleUpdateVO updateVO) {
        sysRoleDao.updateById(commonConvert.updatetoSysRole(updateVO));
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(String id) {
        sysRoleDao.removeById(id);
        sysRolePermissionDao.remove(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getRoleId, id));
        return ResultGenerator.success();
    }

    public List<SysRole> listByUserId(String id) {
        List<String> roleIds = SimpleQuery.list(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, id), SysUserRole::getRoleId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return sysRoleDao.listByIds(roleIds);
    }
}