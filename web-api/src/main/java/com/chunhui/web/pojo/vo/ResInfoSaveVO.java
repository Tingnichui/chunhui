package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.List;


@Data
public class ResInfoSaveVO {


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
