package com.chunhui.web.service;

import com.chunhui.web.dao.SysMenuDao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.SysMenuQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysMenuService {
    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private SysMenuDao sysMenuDao;

    public Result<PageResult<SysMenuOutVO>> pageList(SysMenuQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(sysMenuDao.pageListByQurey(query), commonConvert::toSysMenuOutList));
    }

    public Result<SysMenuOutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.toSysMenuListOut(sysMenuDao.getById(id)));
    }

    public Result<String> save(SysMenuSaveVO saveVO) {
        sysMenuDao.save(commonConvert.toSysMenu(saveVO));
        return ResultGenerator.success();
    }

    public Result<String> update(SysMenuUpdateVO updateVO) {
        sysMenuDao.updateById(commonConvert.updatetoSysMenu(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        sysMenuDao.removeById(id);
        return ResultGenerator.success();
    }

}