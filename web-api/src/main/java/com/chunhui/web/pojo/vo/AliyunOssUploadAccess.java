package com.chunhui.web.pojo.vo;

import lombok.Data;

@Data
public class AliyunOssUploadAccess {

    /**
     * sys_file.id
     */
    private String id;

    /**
     * sys_file.file_path
     */
    private String filePath;

    /**
     * 用户请求的AccessKey ID。
     */
    private String accessid;

    /**
     * 用户表单上传的策略（Policy），Policy为经过Base64编码过的字符串。
     */
    private String policy;

    /**
     * 对Policy签名后的字符串
     */
    private String signature;

    /**
     * 限制上传的文件前缀。
     */
    private String dir;

    /**
     * 用户发送上传请求的域名。
     */
    private String host;

    /**
     * 由服务器端指定的Policy过期时间，格式为Unix时间戳（自UTC时间1970年01月01号开始的秒数）
     */
    private String expire;

}
