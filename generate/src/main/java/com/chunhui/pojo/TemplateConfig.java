package com.chunhui.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 模板配置
 */
@Data
@Accessors(chain = true)
public class TemplateConfig {

    /**
     * 模板名称
     */
    private String name;

    /**
     * 是否覆盖
     */
    private Boolean coverFlag = false;

    /**
     * 生成文件位置
     */
    private String genateDir;

    /**
     * 父目录
     */
    private String fatherDir;

    /**
     * 生成文件前缀
     * 默认是实体类名称+前缀
     */
    private String filePrefix;

    /**
     * 生成文件后缀
     */
    private String fileSuffix;


}
