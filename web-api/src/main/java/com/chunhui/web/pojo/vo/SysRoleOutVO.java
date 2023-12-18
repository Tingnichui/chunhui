package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysRoleOutVO {


    private String id;


    /**
     * 角色名称
     */
    private String roleName;

    private List<String> permissonIdList;


}
