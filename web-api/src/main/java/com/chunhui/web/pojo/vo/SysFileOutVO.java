package com.chunhui.web.pojo.vo;

import lombok.Data;

/**
 * 文件表;
 *
 * @author : genghui
 * @date : 2023-11-25
 */
@Data
public class SysFileOutVO {

    private String id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * url地址
     */
    private String url;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 存储服务
     */
    private String storageServices;

}