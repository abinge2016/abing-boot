package com.abinge.boot.staging.checkauth;


import com.abinge.boot.staging.exceptions.BizException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author lkk
 * @version 1.0
 * @date 2021/4/12 15:52
 */
@Slf4j
public class ExportExcelUtil {


    private static final int threadNum = 64;

    /**
     * 写文件
     *
     * @param xssfWorkbook
     * @param fileName
     * @return
     */
    public static void writeFile(XSSFWorkbook xssfWorkbook, String fileName) {
        OutputStream out = null;
        try {
            //以附件的形式把文件发送到客户端
            out = new FileOutputStream(fileName);
            xssfWorkbook.write(out);
            log.info("写入表格成功：{}",fileName);
        } catch (Exception e) {
            log.error("文件导出失败：{}", fileName, e);
            throw new BizException("文件导出失败");
        }finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param title    excel标题
     * @param headers  表头数据
     * @param fields   表头字段
     * @param dataList 数据
     * @param <T>
     * @return
     */
    public static <T> XSSFWorkbook exportExcel(String title, String[] headers, String[] fields,
                                               List<T> dataList) {
        //创建工作表实例
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建sheet表
        XSSFSheet excelSheet = createSheet(title, workbook);
        //创建首行
        createHeadTitle(workbook, excelSheet, headers);
        if (CollectionUtils.isEmpty(dataList)) {
            return workbook;
        }
        int row = 1;
        //从第二行开始写入数据
//        writeData(fields, dataList, excelSheet, workbook, row);
//        return workbook;
        if (dataList.size() <= threadNum) {
            //从第二行开始写入数据
            writeData(fields, dataList, excelSheet, workbook, row);
            return workbook;
        }
        //数据拆分
        List<List<T>> dataParList = Lists.partition(dataList, threadNum);
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        //开始写入数据
        for (List<T> list : dataParList) {
            int finalRow = row;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> writeData(fields, list, excelSheet, workbook, finalRow));
            futureList.add(future);
            row = row + threadNum;
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
        return workbook;
    }

    /**
     * 写入数据
     *
     * @param fields
     * @param dataList
     * @param excelSheet
     */
    private static <T> void writeData(String[] fields, List<T> dataList, XSSFSheet excelSheet, XSSFWorkbook workbook, int row) {
        //设置样式
        XSSFCellStyle cellStyle = setExcelStyle(workbook);
        // 遍历集合数据，产生数据行
        for (T t : dataList) {
            XSSFRow excelRow = excelSheet.createRow(row);
            for (int j = 0; j < fields.length; j++) {
                XSSFCell xssfCell = excelRow.createCell(j);
                xssfCell.setCellStyle(cellStyle);
                Object object = BeanUtil.getValueByFieldName(fields[j], t);
                handleValue(object, xssfCell);
                //调整每一列宽度
                excelSheet.autoSizeColumn(j);
                // 解决自动设置列宽中文失效的问题
                excelSheet.setColumnWidth(j, excelSheet.getColumnWidth(j) * 15 / 10);
            }
            row = 1 + row;
            log.info("row:{}",row);
        }
    }

    /**
     * 数据处理
     *
     * @param value
     * @param xssfCell
     */
    private static void handleValue(Object value, XSSFCell xssfCell) {
        if (value == null) {
            xssfCell.setCellValue("");
        } else if (value instanceof Number) {
            xssfCell.setCellValue(value.toString());
        } else if (value instanceof Date) {
            Date date = (Date) value;
            xssfCell.setCellValue(TimeUtil.dateToYmdhmsStr(date));
        } else {
            // 其它数据类型都当作字符串简单处理
            xssfCell.setCellValue(value.toString());
        }

    }

    /**
     * 创建sheet表
     *
     * @param xssfWorkbook
     * @return
     */
    private static XSSFSheet createSheet(String title, XSSFWorkbook xssfWorkbook) {
        XSSFSheet excelSheet = xssfWorkbook.createSheet(title);//创建sheet表
        excelSheet.autoSizeColumn(1); //设置样式 自适应列宽
        excelSheet.autoSizeColumn(1, true);
        return excelSheet;
    }

    /**
     * 设置样式
     *
     * @param xssfWorkbook
     * @return
     */
    private static XSSFCellStyle setExcelStyle(XSSFWorkbook xssfWorkbook) {
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();//设置样式 居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 创建首行
     *
     * @param workbook
     * @param excelSheet
     * @param headers
     */
    private static void createHeadTitle(XSSFWorkbook workbook, XSSFSheet excelSheet, String[] headers) {
        XSSFFont font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.index);
        font.setBold(true);
        XSSFCellStyle cellStyle = setExcelStyle(workbook);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFRow titleRow = excelSheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = titleRow.createCell(i, CellType.STRING);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headers[i]);
            //调整每一列宽度
            excelSheet.autoSizeColumn(i);
            // 解决自动设置列宽中文失效的问题
            excelSheet.setColumnWidth(i, excelSheet.getColumnWidth(i) * 15 / 10);
        }
    }
}
