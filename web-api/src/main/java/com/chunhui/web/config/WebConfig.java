package com.chunhui.web.config;

import com.chunhui.web.interceptor.WebInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Value("${ignore.url}")
    private List<String> ignoreUrl;

    @Resource
    private WebInterceptor webInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截所有请求
        registry.addInterceptor(webInterceptor)
                .excludePathPatterns(ignoreUrl)
                .addPathPatterns("/**");
    }

}
