package com.chunhui.web.init;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.aspect.ApiOperation;
import com.chunhui.web.constants.system.UrlAuthStatusEnum;
import com.chunhui.web.dao.SysUrlDao;
import com.chunhui.web.pojo.po.BaseDO;
import com.chunhui.web.pojo.po.SysUrl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component // 注入到容器中
@ConditionalOnWebApplication // 判断是否为Web应用，如果不是则不注入
public class ApiScan implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${ignore.url}")
    private List<String> ignoreUrl;

    @Resource
    private SysUrlDao sysUrlDao;

    private Map<String, String> classMap = new HashMap<>();


    /**
     * 初始化方法
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 服务名称
//        Environment env = applicationContext.getEnvironment();
//        String serviceId = env.getProperty("spring.application.name", "application");

        MDC.put("processId", IdUtil.getSnowflakeNextIdStr());

        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        // 所有接口映射
        RequestMappingHandlerMapping requestMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            // 获取类名
            Class<?> declaringClass = handlerMethod.getMethod().getDeclaringClass();
            final String className = declaringClass.getName();
            // 只对自己的接口做权限控制
            if (!className.startsWith("com.chunhui.web")) {
                continue;
            }
            RequestMappingInfo requestMappingInfo = entry.getKey();
            final String urlPath = StringUtils.join(requestMappingInfo.getPathPatternsCondition().getPatterns(), ",");
            // 方法名
            final String methodName = handlerMethod.getMethod().getName();
            // md5码，根据服务id，请求路径，方法生成，用于判断唯一性
//            String md5 = EncryptUtils.md5Hex(serviceId + urls + methods);
            SysUrl sysUrl = new SysUrl();
            sysUrl.setParentId(getParentId(declaringClass));
            sysUrl.setUrlPath(urlPath);
            sysUrl.setUrlName(methodName);
            sysUrl.setUrlClassName(className);
            sysUrl.setUrlMethodName(methodName);
            // 获取接口描述注解，填充接口描述
            ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                if (StringUtils.isNotBlank(apiOperation.name())) {
                    sysUrl.setUrlName(apiOperation.name());
                }
                sysUrl.setUrlDesc(apiOperation.desc());
            }

            // 插数据库
            SysUrl one = sysUrlDao.getOne(
                    Wrappers.lambdaQuery(SysUrl.class)
                            .eq(SysUrl::getUrlClassName, className)
                            .eq(SysUrl::getUrlPath, urlPath)
                            .select(BaseDO::getId)
            );
            // 默认设置鉴权，如果是忽略接口不设置权限控制
            if (Arrays.stream(urlPath.split(",")).allMatch(v -> ignoreUrl.contains(v))) {
                sysUrl.setUrlAuthStatus(UrlAuthStatusEnum.NO.getKey());
            } else {
                sysUrl.setUrlAuthStatus(UrlAuthStatusEnum.YES.getKey());
            }

            if (null == one) {
                sysUrlDao.save(sysUrl);
            } else {
                sysUrl.setId(one.getId());
                sysUrlDao.updateById(sysUrl);
            }

//            baseApi.setApiCode(md5);
//            baseApi.setRequestMethod(methods);
//            baseApi.setContentType(mediaTypes);
//            baseApi.setServiceId(serviceId);
        }

    }

    private String getParentId(Class<?> declaringClass) {
        String className = declaringClass.getName();
        // 填充controller
        String parentId = classMap.get(className);
        if (StringUtils.isNotBlank(parentId)) {
            return parentId;
        }

        SysUrl parentSysUrl = new SysUrl();
        parentSysUrl.setParentId("0");
        parentSysUrl.setUrlClassName(className);
        String urlPath = StringUtils.join(declaringClass.getAnnotation(RequestMapping.class).value(), ",");
        parentSysUrl.setUrlPath(urlPath);
        // 获取接口描述注解，填充接口描述
        ApiOperation pApiOperation = declaringClass.getAnnotation(ApiOperation.class);
        if (pApiOperation != null) {
            if (StringUtils.isNotBlank(pApiOperation.name())) {
                parentSysUrl.setUrlName(pApiOperation.name());
            }
            parentSysUrl.setUrlDesc(pApiOperation.desc());
        }
        // 插数据库
        SysUrl one = sysUrlDao.getOne(
                Wrappers.lambdaQuery(SysUrl.class)
                        .eq(SysUrl::getParentId, "0")
                        .eq(SysUrl::getUrlClassName, className)
                        .eq(SysUrl::getUrlPath, urlPath)
                        .select(BaseDO::getId)
        );
        if (null == one) {
            sysUrlDao.save(parentSysUrl);
        } else {
            parentSysUrl.setId(one.getId());
            sysUrlDao.updateById(parentSysUrl);
        }
        classMap.put(className, parentSysUrl.getId());
        return parentSysUrl.getId();
    }

}
