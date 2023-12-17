package com.chunhui.web.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.chunhui.web.dao.SysPermissionDao;
import com.chunhui.web.dao.SysResourcePermissionDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.SysPermission;
import com.chunhui.web.pojo.po.SysResourcePermission;
import com.chunhui.web.pojo.query.SysPermissionQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SysPermissionService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private SysPermissionDao sysPermissionDao;

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
        for (String s : saveVO.getResourceIdList()) {
            SysResourcePermission resPer = new SysResourcePermission();
            resPer.setPermissionId(sysPermission.getId());
            resPer.setResourceId(s);
            sysResourcePermissionDao.save(resPer);
        }
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> update(SysPermissionUpdateVO updateVO) {
        sysPermissionDao.updateById(commonConvert.updatetoSysPermission(updateVO));
        sysResourcePermissionDao.remove(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, updateVO.getId()).notIn(SysResourcePermission::getResourceId, updateVO.getResourceIdList()));
        for (String s : updateVO.getResourceIdList()) {
            long count = sysResourcePermissionDao.count(Wrappers.lambdaQuery(SysResourcePermission.class).eq(SysResourcePermission::getPermissionId, updateVO.getId()).eq(SysResourcePermission::getResourceId, s));
            if (count > 0) continue;
            SysResourcePermission resPer = new SysResourcePermission();
            resPer.setPermissionId(updateVO.getId());
            resPer.setResourceId(s);
            sysResourcePermissionDao.save(resPer);
        }
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        sysPermissionDao.removeById(id);
        return ResultGenerator.success();
    }

}