package com.chunhui.web.service.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.dao.SysRolePermissionDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.SysRolePermission;
import com.chunhui.web.pojo.query.SysRolePermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;


@Validated
@Service
public class SysRolePermissionService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    public Result<PageResult<SysRolePermissionOutVO>> pageList(SysRolePermissionQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(sysRolePermissionDao.pageListByQurey(query), commonConvert::toSysRolePermissionOutList));
    }

    public Result<SysRolePermissionOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysRolePermissionListOut(sysRolePermissionDao.getById(id)));
    }


    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(SysRolePermissionSaveVO saveVO) {
        List<String> permissionIdList = saveVO.getPermissionIdList();
        if (null != permissionIdList && !permissionIdList.isEmpty()) {
            sysRolePermissionDao.remove(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getRoleId, saveVO.getRoleId()).notIn(SysRolePermission::getPermissionId, permissionIdList));
            for (String permisId : permissionIdList) {
                long count = sysRolePermissionDao.count(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getRoleId, saveVO.getRoleId()).notIn(SysRolePermission::getPermissionId, permisId));
                if (count > 0) continue;
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setRoleId(saveVO.getRoleId());
                rolePermission.setPermissionId(permisId);
                sysRolePermissionDao.save(rolePermission);
            }
        } else {
            sysRolePermissionDao.remove(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getRoleId, saveVO.getRoleId()));
        }
        return ResultGenerator.success();
    }

    public Result<String> update(SysRolePermissionUpdateVO updateVO) {
        sysRolePermissionDao.updateById(commonConvert.updatetoSysRolePermission(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        sysRolePermissionDao.removeById(id);
        return ResultGenerator.success();
    }

}