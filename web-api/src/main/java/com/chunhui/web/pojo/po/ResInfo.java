package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 资源表;
 *
 * @author : genghui
 * @date : 2023-11-26
 */
@Data
@TableName("res_info")
public class ResInfo extends BaseDO {


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
    private String fileList;

}