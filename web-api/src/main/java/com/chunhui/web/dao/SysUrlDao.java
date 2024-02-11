package com.chunhui.web.dao;

import com.chunhui.web.mapper.SysUrlMapper;
import com.chunhui.web.pojo.po.SysUrl;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class SysUrlDao extends BaseDao<SysUrlMapper, SysUrl> {

    @Resource
    private SysUrlMapper sysUrlMapper;


}