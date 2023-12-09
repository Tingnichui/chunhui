package com.chunhui.web.service;

import com.chunhui.web.dao.${tableBigHump}Dao;
import com.chunhui.web.mapstruct.CommonConvert;
import com.chunhui.web.pojo.query.${tableBigHump}Query;
import com.chunhui.web.pojo.vo.*;
import com.chunhui.web.util.PageUtil;
import com.chunhui.web.util.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ${tableBigHump}Service {
private final CommonConvert commonConvert = CommonConvert.INSTANCE;
@Resource
private ${tableBigHump}Dao ${tableHump}Dao;

public Result
<PageResult
<${tableBigHump}OutVO>> pageList(${tableBigHump}Query query) {
    return ResultGenerator.success(PageUtil.pageResult(${tableHump}Dao.pageListByQurey(query),
    commonConvert::to${tableBigHump}OutList));
    }

    public Result
    <${tableBigHump}OutVO> detail(String id) {
        return ResultGenerator.success(commonConvert.to${tableBigHump}ListOut(${tableHump}Dao.getById(id)));
        }

        public Result
        <String> save(${tableBigHump}SaveVO saveVO) {
            ${tableHump}Dao.save(commonConvert.to${tableBigHump}(saveVO));
            return ResultGenerator.success();
            }

            public Result
            <String> update(${tableBigHump}UpdateVO updateVO) {
                ${tableHump}Dao.updateById(commonConvert.updateto${tableBigHump}(updateVO));
                return ResultGenerator.success();
                }

                public Result
                <String> delete(String id) {
                    ${tableHump}Dao.removeById(id);
                    return ResultGenerator.success();
                    }

                    }