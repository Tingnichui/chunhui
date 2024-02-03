package com.chunhui.web.service.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.chunhui.web.constants.system.PermissionType;
import com.chunhui.web.dao.SysPermissionDao;
import com.chunhui.web.dao.SysResourcePermissionDao;
import com.chunhui.web.dao.SysRolePermissionDao;
import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.exception.ExceptionEnum;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.*;
import com.chunhui.web.pojo.query.SysPermissionQuery;
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
public class SysPermissionService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private SysPermissionDao sysPermissionDao;

    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysResourcePermissionDao sysResourcePermissionDao;

    public Result<PageResult<SysPermissionOutVO>> pageList(SysPermissionQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(sysPermissionDao.pageListByQurey(query), commonConvert::toSysPermissionOutList));
    }

    public Result<SysPermissionOutVO> detail(String id) {
        SysPermissionOutVO out = commonConvert.toSysPermissionListOut(sysPermissionDao.getById(id));
        List<String> resIds = SimpleQuery.list(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, id), SysResourcePermission::getResourceId);
        out.setResourceIdList(resIds);
        return ResultGenerator.success(out);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(SysPermissionSaveVO saveVO) {
        SysPermission sysPermission = commonConvert.toSysPermission(saveVO);
        sysPermissionDao.save(sysPermission);
        List<String> resourceIdList = saveVO.getResourceIdList();
        if (null != resourceIdList && !resourceIdList.isEmpty()) {
            for (String s : resourceIdList) {
                SysResourcePermission resPer = new SysResourcePermission();
                resPer.setPermissionId(sysPermission.getId());
                resPer.setResourceId(s);
                sysResourcePermissionDao.save(resPer);
            }
        }
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> update(SysPermissionUpdateVO updateVO) {
        sysPermissionDao.updateById(commonConvert.updatetoSysPermission(updateVO));
        List<String> resourceIdList = updateVO.getResourceIdList();
        if (null != resourceIdList && !resourceIdList.isEmpty()) {
            sysResourcePermissionDao.remove(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, updateVO.getId()).notIn(SysResourcePermission::getResourceId, resourceIdList));
            for (String s : resourceIdList) {
                long count = sysResourcePermissionDao.count(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, updateVO.getId()).eq(SysResourcePermission::getResourceId, s));
                if (count > 0) continue;
                SysResourcePermission resPer = new SysResourcePermission();
                resPer.setPermissionId(updateVO.getId());
                resPer.setResourceId(s);
                sysResourcePermissionDao.save(resPer);
            }
        } else {
            sysResourcePermissionDao.remove(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, updateVO.getId()));
        }
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(String id) {
        // 查询权限是否被角色使用
        long count = sysRolePermissionDao.count(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getPermissionId, id));
        if (count > 0) {
            return ResultGenerator.fail("该权限已被角色绑定无法删除");
        }

        sysPermissionDao.removeById(id);
        sysResourcePermissionDao.remove(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, id));
        return ResultGenerator.success();
    }

    public List<String> getMenuIdListByCurrentUser() throws Exception {
        SysUser user = sysUserService.getCurrentUser();
        // 获取当前用户所有角色
        List<String> roleIds = SimpleQuery.list(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, user.getId()), SysUserRole::getRoleId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        // 获取角色对应得权限
        List<String> permissonIds = SimpleQuery.list(Wrappers.lambdaQuery(SysRolePermission.class).in(SysRolePermission::getRoleId, roleIds), SysRolePermission::getPermissionId);
        if (CollectionUtils.isEmpty(permissonIds)) {
            return Collections.emptyList();
        }
        // 获取菜单权限列表
        SysPermission permission = sysPermissionDao.getOne(
                Wrappers.lambdaQuery(SysPermission.class)
                        .eq(SysPermission::getPermissionType, PermissionType.MENU.getCode())
                        .in(BaseDO::getId, permissonIds)
                        .last("limit 1")
        );
        if (null == permission) {
            throw new BusinessException(ExceptionEnum.NOT_CONFIGURE_MENU);
        }
        return SimpleQuery.list(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, permission.getId()), SysResourcePermission::getResourceId);
    }
}