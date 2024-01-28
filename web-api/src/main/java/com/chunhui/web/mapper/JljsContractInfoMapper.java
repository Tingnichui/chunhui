package com.chunhui.web.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chunhui.web.pojo.po.JljsContractInfo;


public interface JljsContractInfoMapper extends BaseEntityMapper<JljsContractInfo> {

    IPage<JljsContractInfo> pageListByQurey(IPage page, LambdaQueryWrapper<JljsContractInfo> queryWrapper);

}
