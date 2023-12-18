package com.chunhui.web.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.chunhui.web.dao.SysUserDao;
import com.chunhui.web.exception.BusinessException;
import com.chunhui.web.exception.ExceptionEnum;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.po.SysUser;
import com.chunhui.web.pojo.po.SysUserRole;
import com.chunhui.web.pojo.query.SysUserQuery;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserService {

    private final CommonConvert commonConvert = CommonConvert.INSTANCE;
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private RedisTemplate redisTemplate;

    public String hello() {
        return sysUserDao.hello();
    }

    public SysUser detail() {
        return sysUserDao.getById("1");
    }

    public SysUser getCurrentUser() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("Token");
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ExceptionEnum.NO_LOGIN);
        }
        SysUser user = (SysUser) redisTemplate.opsForValue().get("user:" + token);
        if (null == user) {
            throw new BusinessException(ExceptionEnum.NO_LOGIN);
        }
        return user;
    }

    public Result<String> login(SysUser sysUser) {
        SysUser userInfo = sysUserDao.getOne(
                Wrappers.lambdaQuery(SysUser.class)
                        .eq(SysUser::getUserName, sysUser.getUserName())
                        .eq(SysUser::getPassword, sysUser.getPassword())
        );
        if (null == userInfo) {
            return ResultGenerator.fail("用户不存在");
        }
        String token = IdUtil.fastSimpleUUID();
        redisTemplate.opsForValue().setIfAbsent("user:" + token, userInfo, 12, TimeUnit.HOURS);
        return ResultGenerator.success(token);
    }

    public Result<String> logout(String token) {
        redisTemplate.delete(("user:" + token));
        return ResultGenerator.success();
    }

    public Result<String> regist(SysUser sysUser) {
        sysUserDao.save(sysUser);
        return ResultGenerator.success("注册成功");
    }

    public Result<PageResult<SysUserOutVO>> pageList(SysUserQuery query) {
        return ResultGenerator.success(PageUtil.pageResult(sysUserDao.pageListByQurey(query), commonConvert::toSysUserOutList));
    }

    public Result<SysUserOutVO> detail(String id) {
        SysUserOutVO sysUserListOut = commonConvert.toSysUserListOut(sysUserDao.getById(id));
        List<String> list = SimpleQuery.list(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, id), SysUserRole::getRoleId);
        sysUserListOut.setRoleIdList(list);
        return ResultGenerator.success(sysUserListOut);
    }

    public Result<String> save(SysUserSaveVO saveVO) {
        sysUserDao.save(commonConvert.toSysUser(saveVO));
        return ResultGenerator.success();
    }

    public Result<String> update(SysUserUpdateVO updateVO) {
        sysUserDao.updateById(commonConvert.updatetoSysUser(updateVO));
        return ResultGenerator.success();
    }

    public Result<String> delete(String id) {
        sysUserDao.removeById(id);
        return ResultGenerator.success();
    }

    public Result<SysUserOutVO> getCurrentUserDetail() throws Exception {
        return detail(getCurrentUser().getId());
    }
}
