package com.chunhui.web.mapstruct;

import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.vo.SysUserVO;
import org.mapstruct.Mapper;

@Mapper
public interface CommonConvert {
    SysUserVO toOut(SysUser sysUser);

}
