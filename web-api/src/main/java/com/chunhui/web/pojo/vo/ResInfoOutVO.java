package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResInfoOutVO {


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
     * 文件列表;
     */
    private List<SysFileOutVO> fileList;


}
