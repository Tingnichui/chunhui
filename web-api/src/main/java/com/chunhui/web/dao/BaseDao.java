package com.chunhui.web.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunhui.web.mapper.BaseEntityMapper;
import com.chunhui.web.pojo.query.QueryBase;

/**
 * @author dcy
 * @description 公共dao
 * @createTime 2022/11/8 14:41
 */
public class BaseDao<M extends BaseEntityMapper<T>, T> extends ServiceImpl<M, T> {

    /**
     * 分页查询 根据分页参数查询
     *
     * @param queryBase    分页参数
     * @param queryWrapper 条件构造
     * @return
     */
    public IPage<T> pageList(QueryBase queryBase, Wrapper<T> queryWrapper) {
        return page(Page.of(queryBase.getCurrent(), queryBase.getSize()), queryWrapper);
    }


}
