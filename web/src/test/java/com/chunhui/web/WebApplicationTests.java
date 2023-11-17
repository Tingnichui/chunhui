package com.chunhui.web;

import com.chunhui.web.util.DingdingUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WebApplicationTests {

	@Resource
	private DingdingUtil dingdingUtil;

	@Test
	void testSendMsg() throws Exception {
		dingdingUtil.sendMsg("123");
	}

}
