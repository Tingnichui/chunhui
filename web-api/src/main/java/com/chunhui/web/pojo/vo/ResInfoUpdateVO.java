package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class ResInfoUpdateVO {


    @NotBlank(message = "主键id不能为空")
    private String id;


    /**
     * 标题
     */
    private String title;


    /**
     * 内容
     */
    private String content;

    /**
     * 文件列表;存储sys_file.id
     */
    private List<String> fileList;


}
