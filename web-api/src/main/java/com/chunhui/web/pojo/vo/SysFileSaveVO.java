package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文件表;
 *
 * @author : genghui
 * @date : 2023-11-25
 */
@Data
public class SysFileSaveVO {


    /**
     * 文件名称
     */
    @NotBlank(message = "文件名称不能为空")
    private String fileName;

    /**
     * 文件大小
     */
    @NotNull(message = "文件大小不能为空")
    private Integer fileSize;

    /**
     * 文件类型
     */
    @NotNull(message = "文件类型不能为空")
    private String fileType;

}