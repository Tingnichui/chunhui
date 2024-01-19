package com.chunhui.web.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
* 加励健身-合同操作记录
*/
@Data
@TableName("jljs_contract_operate_record")
public class JljsContractOperateRecord extends BaseDO {



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