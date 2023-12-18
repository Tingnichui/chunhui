package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class SysUserRoleSaveVO {


    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    private String userId;


    /**
     * 角色id
     */
    private List<String> roleIdList;


}
