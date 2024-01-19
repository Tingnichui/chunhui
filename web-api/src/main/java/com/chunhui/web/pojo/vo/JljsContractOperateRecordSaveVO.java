package com.chunhui.web.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class JljsContractOperateRecordSaveVO {


    /**
     * 合同id
     */
    @NotBlank(message = "合同id不能为空")
    private String contractInfoId;


    /**
     * 合同操作类型
     */
    @NotBlank(message = "操作时间不能为空")
    private String contractOperateType;


    /**
     * 间隔天数
     */
    private Integer intervalDays;


    /**
     * 开始时间
     */
    private String operateBeginDate;


    /**
     * 结束时间
     */
    private String operateEndDate;


    /**
     * 操作原因
     */
    private String operateReason;


    /**
     * 操作金额
     */
    private String operateAmount;


}
