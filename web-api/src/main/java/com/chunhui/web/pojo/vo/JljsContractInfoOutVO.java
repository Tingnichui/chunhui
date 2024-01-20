package com.chunhui.web.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class JljsContractInfoOutVO {


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
    @JsonFormat
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


}
