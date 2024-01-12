package com.chunhui.web.mapstruct;

import com.chunhui.web.pojo.po.*;
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

    List<ResInfoOutVO> toResInfoOutList(List<ResInfo> records);

    ResInfoOutVO toResInfoListOut(ResInfo byId);

    ResInfo toResInfo(ResInfoSaveVO saveVO);

    ResInfo updatetoResInfo(ResInfoUpdateVO saveVO);

    ResInfo toPo(ResInfoSaveVO resInfoVO);

    SysFile toPo(SysFileSaveVO sysFileSaveVO);

    List<SysMenuOutVO> toSysMenuOutList(List<SysMenu> records);

    SysMenuOutVO toSysMenuListOut(SysMenu byId);

    SysMenu toSysMenu(SysMenuSaveVO saveVO);

    SysMenu updatetoSysMenu(SysMenuUpdateVO saveVO);


    List<SysRoleOutVO> toSysRoleOutList(List<SysRole> records);

    SysRoleOutVO toSysRoleListOut(SysRole byId);

    SysRole toSysRole(SysRoleSaveVO saveVO);

    SysRole updatetoSysRole(SysRoleUpdateVO saveVO);

    List<SysPermissionOutVO> toSysPermissionOutList(List<SysPermission> records);

    SysPermissionOutVO toSysPermissionListOut(SysPermission byId);

    SysPermission toSysPermission(SysPermissionSaveVO saveVO);

    SysPermission updatetoSysPermission(SysPermissionUpdateVO saveVO);

    List<SysUserOutVO> toSysUserOutList(List<SysUser> records);

    SysUserOutVO toSysUserListOut(SysUser byId);

    SysUser toSysUser(SysUserSaveVO saveVO);

    SysUser updatetoSysUser(SysUserUpdateVO saveVO);

    List<SysUserRoleOutVO> toSysUserRoleOutList(List<SysUserRole> records);

    SysUserRoleOutVO toSysUserRoleListOut(SysUserRole byId);

    SysUserRole toSysUserRole(SysUserRoleSaveVO saveVO);

    SysUserRole updatetoSysUserRole(SysUserRoleUpdateVO saveVO);

    List<SysRolePermissionOutVO> toSysRolePermissionOutList(List<SysRolePermission> records);

    SysRolePermissionOutVO toSysRolePermissionListOut(SysRolePermission byId);

    SysRolePermission toSysRolePermission(SysRolePermissionSaveVO saveVO);

    SysRolePermission updatetoSysRolePermission(SysRolePermissionUpdateVO saveVO);

    List<JljsMemberInfoOutVO> toJljsMemberInfoOutList(List<JljsMemberInfo> records);

    JljsMemberInfoOutVO toJljsMemberInfoListOut(JljsMemberInfo byId);

    JljsMemberInfo toJljsMemberInfo(JljsMemberInfoSaveVO saveVO);

    JljsMemberInfo updatetoJljsMemberInfo(JljsMemberInfoUpdateVO saveVO);

    List<JljsCoachInfoOutVO> toJljsCoachInfoOutList(List<JljsCoachInfo> records);

    JljsCoachInfoOutVO toJljsCoachInfoListOut(JljsCoachInfo byId);

    JljsCoachInfo toJljsCoachInfo(JljsCoachInfoSaveVO saveVO);

    JljsCoachInfo updatetoJljsCoachInfo(JljsCoachInfoUpdateVO saveVO);

    List<JljsCourseInfoOutVO> toJljsCourseInfoOutList(List<JljsCourseInfo> records);

    JljsCourseInfoOutVO toJljsCourseInfoListOut(JljsCourseInfo byId);

    JljsCourseInfo toJljsCourseInfo(JljsCourseInfoSaveVO saveVO);

    JljsCourseInfo updatetoJljsCourseInfo(JljsCourseInfoUpdateVO saveVO);

    List<JljsMemberOrderOutVO> toJljsMemberOrderOutList(List<JljsMemberOrder> records);

    JljsMemberOrderOutVO toJljsMemberOrderListOut(JljsMemberOrder byId);

    JljsMemberOrder toJljsMemberOrder(JljsMemberOrderSaveVO saveVO);

    JljsMemberOrder updatetoJljsMemberOrder(JljsMemberOrderUpdateVO saveVO);

    List<JljsContractOperateOutVO> toJljsContractOperateOutList(List<JljsContractOperate> records);

    JljsContractOperateOutVO toJljsContractOperateListOut(JljsContractOperate byId);

    JljsContractOperate toJljsContractOperate(JljsContractOperateSaveVO saveVO);

    JljsContractOperate updatetoJljsContractOperate(JljsContractOperateUpdateVO saveVO);

    List<JljsClassRecordOutVO> toJljsClassRecordOutList(List<JljsClassRecord> records);

    JljsClassRecordOutVO toJljsClassRecordListOut(JljsClassRecord byId);

    JljsClassRecord toJljsClassRecord(JljsClassRecordSaveVO saveVO);

    JljsClassRecord updatetoJljsClassRecord(JljsClassRecordUpdateVO saveVO);
}
