package com.chunhui.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@MapperScan("com.chunhui.web.mapper")
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebApplication.class, args);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("server.port");
        String path = environment.getProperty("server.servlet.context-path");
        log.info("application start success \t http://localhost:{}{}", property, path);
    }

}
