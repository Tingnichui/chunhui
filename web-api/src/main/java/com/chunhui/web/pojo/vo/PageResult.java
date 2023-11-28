package com.chunhui.web.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private long current;

    private long pages;

    private long size;

    private long total;

    private List<T> records;

}
