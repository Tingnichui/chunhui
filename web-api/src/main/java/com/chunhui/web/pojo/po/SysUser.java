package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 系统用户表;
 *
 * @author : genghui
 * @date : 2023-11-25
 */
@Data
@TableName("sys_user")
public class SysUser extends BaseDO {


    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 密码
     */
    private String password;

}