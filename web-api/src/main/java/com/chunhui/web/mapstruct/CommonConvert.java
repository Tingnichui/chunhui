package com.chunhui.web.mapstruct;

import com.chunhui.web.pojo.po.ResInfo;
import com.chunhui.web.pojo.po.SysFile;
import com.chunhui.web.pojo.po.SysMenu;
import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = TypeConvert.class)
public interface CommonConvert {
    CommonConvert INSTANCE = Mappers.getMapper(CommonConvert.class);

    SysUserVO toOut(SysUser sysUser);

    SysFileOutVO toOut(SysFile sysFile);

    List<SysFileOutVO> toOutList(List<SysFile> sysFile);

    List<ResInfoOutVO> toResOutList(List<ResInfo> list);

    ResInfoOutVO toOut(ResInfo resInfo);

    ResInfo toPo(ResInfoSaveVO resInfoVO);

    SysFile toPo(SysFileSaveVO sysFileSaveVO);

    List<SysMenuOutVO> toSysMenuOutList(List<SysMenu> records);

    SysMenuOutVO toSysMenuListOut(SysMenu byId);

    SysMenu toSysMenu(SysMenuSaveVO saveVO);

    SysMenu updatetoSysMenu(SysMenuUpdateVO saveVO);
}
