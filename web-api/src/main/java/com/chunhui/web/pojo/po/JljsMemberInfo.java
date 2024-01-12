package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 加励健身-会员信息
 */
@Data
@TableName("jljs_member_info")
public class JljsMemberInfo extends BaseDO {


    /**
     * 会员姓名
     */
    private String memberName;


    /**
     * 会员性别;1 男；2女
     */
    private String memberGender;


    /**
     * 会员年龄
     */
    private String memberAge;


    /**
     * 手机号
     */
    private String memberPhoneNum;


}