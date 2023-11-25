package com.chunhui.web.util;

import com.alibaba.fastjson.JSON;
import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.exception.ExceptionEnum;
import com.chunhui.web.pojo.po.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ServerletUtil {
    public static SysUser getUserInfo() throws BusinessException {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        String userInfoStr = (String) request.getAttribute("userInfo");
        if (StringUtils.isBlank(userInfoStr)) {
            throw new BusinessException(ExceptionEnum.NO_LOGIN);
        }
        return JSON.parseObject(userInfoStr, SysUser.class);
    }
}
