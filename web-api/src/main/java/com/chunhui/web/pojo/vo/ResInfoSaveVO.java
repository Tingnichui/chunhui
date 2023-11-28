package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@Data
public class ResInfoSaveVO {

    private String id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 文件列表;存储sys_file.id
     */
    private List<String> fileList;

}