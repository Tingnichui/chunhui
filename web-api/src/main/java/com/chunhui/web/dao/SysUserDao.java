package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.SysUserMapper;
import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.query.SysUserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SysUserDao extends BaseDao<SysUserMapper, SysUser> {

    @Resource
    private SysUserMapper sysUserMapper;

    public String hello() {
        return sysUserMapper.hello();
    }

    public IPage<SysUser> pageListByQurey(SysUserQuery query) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getUserName()), SysUser::getUserName, query.getUserName());
        queryWrapper.like(StringUtils.isNotBlank(query.getNickName()), SysUser::getNickName, query.getNickName());
        queryWrapper.like(StringUtils.isNotBlank(query.getPhoneNum()), SysUser::getPhoneNum, query.getPhoneNum());
        queryWrapper.like(StringUtils.isNotBlank(query.getPassword()), SysUser::getPassword, query.getPassword());
        return super.pageList(query, queryWrapper);
    }

}
