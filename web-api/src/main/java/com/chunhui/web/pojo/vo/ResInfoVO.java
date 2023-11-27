package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@Data
public class ResInfoVO {


    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 文件列表;json格式，存储sys_file.id
     */
    private List<String> fileList;

}