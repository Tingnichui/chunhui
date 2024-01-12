package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 加励健身-合同操作记录
 */
@Data
@TableName("jljs_contract_operate")
public class JljsContractOperate extends BaseDO {


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