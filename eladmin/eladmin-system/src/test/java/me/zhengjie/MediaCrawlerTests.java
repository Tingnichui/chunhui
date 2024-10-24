package me.zhengjie;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import me.zhengjie.mediacrawler.task.MediaCrawlerTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MediaCrawlerTests {

    @Resource
    private MediaCrawlerTask mediaCrawlerTask;

    @Test
    void crawl() {
        mediaCrawlerTask.crawl("手办");
//        mediaCrawlerTask.checkAccountValidStatus();
    }

    @Test
    void syncCrawlRecord() throws Exception {
        mediaCrawlerTask.syncCrawlRecord(DateUtil.parse("2024-09-22", DatePattern.NORM_DATE_PATTERN));
    }

    @Test
    void name() throws Exception {
        mediaCrawlerTask.syncRecentCrawlRecord("3");
    }
}

