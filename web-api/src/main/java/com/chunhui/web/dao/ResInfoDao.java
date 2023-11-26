package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.mapper.ResInfoMapper;
import com.chunhui.web.pojo.po.ResInfo;
import com.chunhui.web.pojo.query.ResInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * res_info 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@Repository
public class ResInfoDao extends BaseDao<ResInfoMapper, ResInfo> {
    @Resource
    private ResInfoMapper resInfoMapper;

    public IPage<ResInfo> pageListByQurey(ResInfoQuery query) {
        LambdaQueryWrapper<ResInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(query.getTitle()), ResInfo::getTitle, query.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(query.getContent()), ResInfo::getContent, query.getContent());
        return super.pageList(query, queryWrapper);
    }
}