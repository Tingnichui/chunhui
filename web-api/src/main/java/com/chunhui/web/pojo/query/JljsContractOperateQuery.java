package com.chunhui.web.pojo.query;

import lombok.Data;


@Data
public class JljsContractOperateQuery extends QueryBase {

    private String id;


    /**
     * 课程id
     */
    private String courseInfoId;


    /**
     * 操作类型;1暂停；2补缴；3退课
     */
    private String operateType;


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
