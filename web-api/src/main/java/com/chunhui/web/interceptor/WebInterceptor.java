package com.chunhui.web.interceptor;

import cn.hutool.core.util.IdUtil;
import com.chunhui.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

    @Value("${ignore.url}")
    private List<String> ignoreUrl;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SysUserService sysUserService;

    /**
     * 方法执行前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // mdc放入执行链路id
        MDC.put("processId", IdUtil.getSnowflakeNextIdStr());
        // 放入请求开始时间
        request.setAttribute("startTime", System.currentTimeMillis());
        if (!ignoreUrl.contains(request.getServletPath())) {
            // 登录鉴权
            request.setAttribute("userInfo", sysUserService.getCurrentUser());
        }
        return true;
    }

    /**
     * Controller方法调用之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.err.println("方法回调" + request.getRequestURL());
    }

    /**
     * postHandler后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 计算耗时
        long diffTime = (System.currentTimeMillis() - (long) request.getAttribute("startTime")) / 1000;
        if (diffTime > 60) {
            log.info("{} 耗时过长 {} 秒", request.getServletPath(), diffTime);
        }
    }
}
