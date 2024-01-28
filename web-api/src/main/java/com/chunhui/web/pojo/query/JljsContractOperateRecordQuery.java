package com.chunhui.web.pojo.query;

import lombok.Data;


@Data
public class JljsContractOperateRecordQuery extends QueryBase {

    private String id;


    /**
     * 合同id
     */
    private String contractInfoId;


    /**
     * 合同操作类型
     */
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
