package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 系统用户角色
 */
@Data
@TableName("sys_user_role")
public class SysUserRole extends BaseDO {


    /**
     * 用户id
     */
    private String userId;


    /**
     * 角色id
     */
    private String roleId;


}