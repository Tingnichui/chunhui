package com.chunhui.web.pojo.query;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class JljsContractInfoQuery extends QueryBase {

    private String id;


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
    private String useBeginDate;


    /**
     * 使用结束日期
     */
    private String useEndDate;


    /**
     * 购买日期
     */
    private String buyTime;


    /**
     * 合同备注
     */
    private String contractRemark;


    /**
     * 实际收取金额
     */
    private String actualChargeAmount;


}
