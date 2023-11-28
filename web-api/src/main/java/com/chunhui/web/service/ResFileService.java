package com.chunhui.web.service;

import com.chunhui.web.dao.ResFileDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * res_file 资源文件;
 *
 * @author : genghui
 * @date : 2023-11-28
 */
@Service
public class ResFileService {
    @Resource
    private ResFileDao resFileDao;
}