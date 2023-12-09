package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.SysMenuMapper;
import com.chunhui.web.pojo.po.SysMenu;
import com.chunhui.web.pojo.query.SysMenuQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysMenuDao extends BaseDao<SysMenuMapper, SysMenu> {

    @Resource
    private SysMenuMapper sysMenuMapper;

    public IPage<SysMenu> pageListByQurey(SysMenuQuery query) {
        LambdaQueryWrapper<SysMenu> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getName()), SysMenu::getName, query.getName());
        queryWrapper.like(StringUtils.isNotBlank(query.getUrl()), SysMenu::getUrl, query.getUrl());
        return super.pageList(query, queryWrapper);
    }

}