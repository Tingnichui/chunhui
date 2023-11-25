package com.chunhui.web.dao;

import com.chunhui.web.mapper.SysFileMapper;
import com.chunhui.web.pojo.po.SysFile;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SysFileDao extends BaseDao<SysFileMapper, SysFile> {

    @Resource
    private SysFileMapper sysFileMapper;

}
