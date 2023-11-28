package com.chunhui.web.dao;

import com.chunhui.web.mapper.ResFileMapper;
import com.chunhui.web.pojo.po.ResFile;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * res_file 资源文件;
 *
 * @author : genghui
 * @date : 2023-11-28
 */
@Repository
public class ResFileDao extends BaseDao<ResFileMapper, ResFile> {
    @Resource
    private ResFileMapper resFileMapper;
}