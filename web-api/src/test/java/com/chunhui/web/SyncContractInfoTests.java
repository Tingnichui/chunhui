package com.chunhui.web;

import com.chunhui.web.task.SyncContractInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SyncContractInfoTests {

    @Resource
    private SyncContractInfo syncContractInfo;

    @Test
    void test() {
        syncContractInfo.syncAll();
    }
}
