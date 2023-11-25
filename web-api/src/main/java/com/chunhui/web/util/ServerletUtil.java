package com.chunhui.web.util;

import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.exception.ExceptionEnum;
import com.chunhui.web.pojo.po.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ServerletUtil {
    public static SysUser getUserInfo() throws BusinessException {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        SysUser user = (SysUser) request.getAttribute("userInfo");
        if (null == user) {
            throw new BusinessException(ExceptionEnum.NO_LOGIN);
        }
        return user;
    }
}
