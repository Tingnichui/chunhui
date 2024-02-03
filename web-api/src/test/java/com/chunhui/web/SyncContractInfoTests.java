package com.chunhui.web;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chunhui.web.constants.jljs.JljsContractOperateTypeEnum;
import com.chunhui.web.constants.jljs.JljsContractStatusEnum;
import com.chunhui.web.dao.JljsContractInfoDao;
import com.chunhui.web.pojo.po.JljsContractInfo;
import com.chunhui.web.pojo.vo.JljsContractOperateRecordSaveVO;
import com.chunhui.web.pojo.vo.Result;
import com.chunhui.web.service.jljs.JljsContractInfoService;
import com.chunhui.web.service.jljs.JljsContractOperateRecordService;
import com.chunhui.web.task.SyncContractInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class SyncContractInfoTests {

    @Resource
    private SyncContractInfo syncContractInfo;
    @Resource
    private JljsContractOperateRecordService jljsContractOperateRecordService;
    @Resource
    private JljsContractInfoService jljsContractInfoService;
    @Resource
    private JljsContractInfoDao jljsContractInfoDao;

    @Test
    void test() {
        syncContractInfo.syncAll();
    }

    /**
     * 批量保存合同记录
     */
    @Test
    void saveOperateRecord() throws Exception {
        try {
            MDC.put("processId", IdUtil.getSnowflakeNextIdStr());
            
            // 获取所有正在使用的合同
            List<JljsContractInfo> contractInfoList = jljsContractInfoDao.list(
                    Wrappers.lambdaQuery(JljsContractInfo.class)
                            .eq(JljsContractInfo::getContractStatus, JljsContractStatusEnum.shiyong.getCode())
            );

            List<String> errorList = new ArrayList<>();

            // 遍历生成请假记录
            JljsContractOperateRecordSaveVO saveVO = new JljsContractOperateRecordSaveVO();
            saveVO.setContractOperateType(JljsContractOperateTypeEnum.zanting.getCode());
            saveVO.setOperateBeginDate(DateUtil.parse("2024-02-09", DatePattern.NORM_DATE_PATTERN));
            saveVO.setOperateEndDate(DateUtil.parse("2024-02-18", DatePattern.NORM_DATE_PATTERN));
            saveVO.setOperateReason("春节（2024-02-09~2024-02-18）暂停营业");
            for (JljsContractInfo contractInfo : contractInfoList) {
                saveVO.setContractInfoId(contractInfo.getId());
                Result<String> save = jljsContractOperateRecordService.save(saveVO);
                if (!save.success()) {
                    errorList.add(contractInfo.getId());
                }
            }

            if (!errorList.isEmpty()) {
                System.err.println(JSON.toJSONString(errorList));
            }
        } catch (Exception e) {
            log.error("出现异常", e);
        }

    }
}
