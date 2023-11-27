package com.chunhui.web;

import com.chunhui.web.pojo.vo.AliyunOssUploadAccess;
import com.chunhui.web.util.AliyunOssUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AliyunOssUtilTests {

    @Resource
    private AliyunOssUtil aliyunOssUtil;

    @Test
    void testtGetUploadAccess() throws Exception {
        AliyunOssUploadAccess uploadAccess = aliyunOssUtil.getUploadAccess("test/123", "");
        System.err.println(uploadAccess);
    }

    @Test
    void name() {
        String downLoadAccess = aliyunOssUtil.getDownLoadAccess("fei-a", "user/1728284047377879041/1701014960-images.jpg");
        System.err.println(downLoadAccess);
    }
}
