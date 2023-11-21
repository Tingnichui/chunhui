package com.chunhui.web.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunhui.web.mapper.BaseEntityMapper;

/**
 * @author dcy
 * @description 公共dao
 * @createTime 2022/11/8 14:41
 */
public class BaseDao<M extends BaseEntityMapper<T>, T> extends ServiceImpl<M, T> {

}
