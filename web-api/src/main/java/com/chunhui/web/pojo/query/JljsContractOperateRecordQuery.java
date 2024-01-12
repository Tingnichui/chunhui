package com.chunhui.web.pojo.query;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class JljsContractOperateRecordQuery extends QueryBase {

    private String id;


    /**
    * heto
    */
    private String contractInfoId;


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
