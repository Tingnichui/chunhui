package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@Data
public class ResInfoOutVO {

    private String id;

    private String title;

    private String content;

    private Date createTime;

    /**
     * 文件列表;
     */
    private List<SysFileOutVO> fileList;

}