package com.chunhui.web.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chunhui.web.exception.ExceptionEnum;
import com.chunhui.web.util.ServerletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    public static final String CREATE_BY = "createBy";
    public static final String CREATE_TIME = "createTime";
    public static final String UPDATE_BY = "updateBy";
    public static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 填充创建人id
        try {
            this.strictInsertFill(metaObject, CREATE_BY, String.class, ServerletUtil.getUserInfo().getId());
        } catch (Exception e) {
            // 判断是不是web现成 1.是:填充线程id 2.不是就填充方法名
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                String processId = MDC.get("processId");
                if (StringUtils.isBlank(processId)) {
                    throw new RuntimeException(ExceptionEnum.SAVE_FAIL.getMessage());
                }
                this.strictInsertFill(metaObject, CREATE_BY, String.class, processId);
            } else {
                this.strictInsertFill(metaObject, CREATE_BY, String.class, "task");
            }

        }
        // 自动填充创建时间
        this.strictInsertFill(metaObject, CREATE_TIME, Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 填充创建人id
        try {
            this.strictUpdateFill(metaObject, UPDATE_BY, String.class, ServerletUtil.getUserInfo().getId());
        } catch (Exception e) {
            // 判断是不是web现成 1.是:填充线程id 2.不是就填充方法名
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                // 获取当前线程id
                String processId = MDC.get("processId");
                if (StringUtils.isBlank(processId)) {
                    throw new RuntimeException(ExceptionEnum.SAVE_FAIL.getMessage());
                }
                this.strictUpdateFill(metaObject, UPDATE_BY, String.class, processId);
            } else {
                this.strictUpdateFill(metaObject, UPDATE_BY, String.class, "task");
            }

        }
        // 自动填充创建时间
        this.strictUpdateFill(metaObject, UPDATE_TIME, Date.class, new Date());
    }

}
