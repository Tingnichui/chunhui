package com.chunhui.web;

import com.chunhui.web.util.AliyunOssUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AliyunOssUtilTests {

    @Resource
    private AliyunOssUtil aliyunOssUtil;

    @Test
    void name() throws Exception {
//        Result<AliyunOssUploadAccess> test = aliyunOssUtil.getUploadAccess("test/123");
//        System.err.println(test);
    }
}
