package com.chunhui.web.init;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
//@Component
@Order(1)
public class CommandLineInitResource implements CommandLineRunner, ApplicationContextAware {

    // 定义一个私有的方便本class中调用
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : methodMap.keySet()) {

            Set<String> urlSet = null;
            if (info.getPatternsCondition() != null) {
                urlSet = info.getPatternsCondition().getPatterns();
                // 获取全部请求方式
                Set<RequestMethod> Methods = info.getMethodsCondition().getMethods();
                System.out.println(Methods.toString());
                for (String url : urlSet) {
                    // 加上自己的域名和端口号，就可以直接调用
                    urlList.add("http://localhost:XXXX" + url);
                }
            }

        }
        System.err.println(JSON.toJSONString(urlList));

    }


}
