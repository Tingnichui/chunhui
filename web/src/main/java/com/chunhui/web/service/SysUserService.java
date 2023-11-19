package com.chunhui.web.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.dao.SysUserDao;
import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.exception.ExceptionEnum;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.pojo.vo.SysUserVO;
import com.chunhui.web.util.ResultGeneretor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private RedisTemplate redisTemplate;

    private CommonConvert commonConvert;

    public String hello() {
        return sysUserDao.hello();
    }

    public SysUser detail() {
        return sysUserDao.getById("1");
    }

    public SysUserVO getCurrentUser() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ExceptionEnum.NO_LOGIN);
        }
        SysUser user = (SysUser) redisTemplate.opsForValue().get("user:" + token);
        if (null == user) {
            throw new BusinessException(ExceptionEnum.NO_LOGIN);
        }
        return commonConvert.toOut(user);
    }

    public Result<String> login() {
        SysUser one = sysUserDao.getOne(Wrappers.lambdaQuery(SysUser.class).last("limit 1"));
        if (null == one) {
            return ResultGeneretor.fail("用户不存在");
        }
        String token = IdUtil.fastSimpleUUID();
        redisTemplate.opsForValue().setIfAbsent("user:" + token, one, 12, TimeUnit.HOURS);
        return ResultGeneretor.success(token);
    }

    public Result<String> logout(String token) {
        redisTemplate.delete(("user:" + token));
        return ResultGeneretor.success();
    }
}
