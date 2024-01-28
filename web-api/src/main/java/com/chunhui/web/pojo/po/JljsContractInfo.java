package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 加励健身-会员合同
 */
@Data
@TableName("jljs_contract_info")
public class JljsContractInfo extends BaseDO {


    /**
     * 会员id
     */
    private String memberId;


    /**
     * 课程id
     */
    private String courseInfoId;


    /**
     * 开单教练id
     */
    private String belongCoachId;


    /**
     * 合同金额
     */
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

    /**
     * 剩余数量（天/次）
     */
    private Integer courseRemainQuantity;

    /**
     * 已使用数量（天/次）
     */
    private Integer courseUseQuantity;

    /**
     * 课程总暂停天数
     */
    private Integer courseTotalStopDays;

}