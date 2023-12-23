package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 用户角色
 */
@Data
@TableName("sys_role")
public class SysRole extends BaseDO {


    /**
     * 角色名称
     */
    private String roleName;


}