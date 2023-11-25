package com.chunhui.web.mapstruct;

import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.vo.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommonConvert {
    CommonConvert INSTANCE = Mappers.getMapper(CommonConvert.class);

    SysUserVO toOut(SysUser sysUser);

}
