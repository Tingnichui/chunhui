package com.chunhui.web.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chunhui.web.pojo.vo.PageResult;

import java.util.List;
import java.util.function.Function;

public class PageUtil {


    public static <DTO, T> PageResult<DTO> pageResult(IPage<T> iPage, Function<List<T>, List<DTO>> function) {
        PageResult<DTO> page = new PageResult<>();
        page.setCurrent(iPage.getCurrent());
        page.setPages(iPage.getPages());
        page.setSize(iPage.getSize());
        page.setTotal(iPage.getTotal());
        page.setRecords(function.apply(iPage.getRecords()));
        return page;
    }

}
