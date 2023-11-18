package com.chunhui.web;

import cn.hutool.core.util.IdUtil;
import com.chunhui.web.util.DingdingUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootTest
class WebApplicationTests {

    @Resource
    private DingdingUtil dingdingUtil;

    @Test
    void testSendMsg() throws Exception {
        dingdingUtil.sendMsg("123");
    }

    @Test
    void test() throws Exception {
        File tempFile = File.createTempFile(IdUtil.getSnowflakeNextIdStr(), ".docx");
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile.getAbsolutePath())))) {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setClassForTemplateLoading(this.getClass(), "/templates");

            Map<String, String> map = new HashMap<>();
            map.put("name", "test123");
            // 加载模板
            Template template = configuration.getTemplate("test.ftl");
            // 填充数据
            template.process(map, writer);
            writer.flush();
        } finally {
            System.err.println(tempFile.delete());
        }
    }


}
