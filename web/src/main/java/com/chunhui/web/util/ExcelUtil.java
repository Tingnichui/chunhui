package com.chunhui.web.util;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExcelUtil {

    public static SXSSFWorkbook doCreateSheet(SXSSFWorkbook workbook, String sheetName, List<String> title, List<List<String>> rows) {
        // 先获取sheet 如果不存在则创建
        SXSSFSheet sheet = workbook.getSheet(sheetName);
        if (null == sheet) {
            sheet = workbook.createSheet(sheetName);
        }
        // 如果还未写入数据 创建标题
        int lastRowNum = sheet.getLastRowNum();
        if (-1 == lastRowNum) {
            SXSSFRow titleRow = sheet.createRow(0);
            for (int j = 0; j < title.size(); j++) {
                titleRow.createCell(j).setCellValue(StringUtils.isNotBlank(title.get(j)) ? title.get(j) : "");
            }
        }
        // 填充内容
        for (int i = 0; i < rows.size(); i++) {
            List<String> list = rows.get(i);
            int lastRowNum1 = sheet.getLastRowNum();
            SXSSFRow row = sheet.createRow(lastRowNum1 + 1);
            for (int j = 0; j < list.size(); j++) {
                row.createCell(j).setCellValue(StringUtils.isNotBlank(list.get(j)) ? list.get(j) : "");
            }
        }
        return workbook;
    }

    public static SXSSFWorkbook createSheet(SXSSFWorkbook workbook, String sheetName, String title, List<List<String>> rows) {
        List<String> titleList = Arrays.asList(title.split(","));
        return doCreateSheet(workbook, sheetName, titleList, rows);
    }

    public static File createSheet(String sheetName, String title, List<List<String>> rows) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        createSheet(workbook, sheetName, title, rows);
        File tempFile = TempFile.createTempFile(IdUtil.getSnowflakeNextIdStr(), ".xlsx");
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            workbook.write(outputStream);
        } finally {
            workbook.dispose();
        }
        return tempFile;
    }

}
