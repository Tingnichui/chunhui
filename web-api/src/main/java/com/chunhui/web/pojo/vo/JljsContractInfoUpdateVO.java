package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class JljsContractInfoUpdateVO {


    @NotBlank(message = "主键id不能为空")
    private String id;


    /**
     * 会员id
     */
    @NotBlank(message = "会员不能为空")
    private String memberId;


    /**
     * 课程id
     */
    @NotBlank(message = "课程不能为空")
    private String courseInfoId;


    /**
     * 开单教练id
     */
    @NotBlank(message = "开单教练不能为空")
    private String belongCoachId;


    /**
     * 合同金额
     */
    @NotNull(message = "合同金额不能为空")
    private BigDecimal contractAmount;


    /**
     * 合同状态;1 未生效；2生效中；3完成；4暂停；5终止；
     */
    private String contractStatus;


    /**
     * 使用开始日期
     */
    private Date useBeginDate;


    /**
     * 使用结束日期
     */
    private Date useEndDate;


    /**
     * 购买日期
     */
    private Date buyTime;


    /**
     * 合同备注
     */
    private String contractRemark;


    /**
     * 实际收取金额
     */
    @NotNull(message = "实际收取金额不能为空")
    private BigDecimal actualChargeAmount;

    /**
     * 课程类型；1按次消费；2按天计时；
     */
    private String courseType;

    /**
     * 有效期限（天）
     */
    private Integer courseUsePeriodDays;

    /**
     * 可使用数量（天/次）
     */
    private Integer courseAvailableQuantity;

}
