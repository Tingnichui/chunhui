package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


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
    @NotBlank(message = "合同金额不能为空")
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
    @NotBlank(message = "实际收取金额不能为空")
    private String actualChargeAmount;


}
