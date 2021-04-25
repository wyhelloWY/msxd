package com.landian.mashangxiadan.utils;

import org.apache.poi.ss.usermodel.*;

import java.text.SimpleDateFormat;

/**
 * @author Yu W
 * @date 2020/7/19 9:43
 */
public class ReadExcelTest {
    public static String getValue(Cell cell){
        String cellValue = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(cell.getDateCellValue());
                }else{
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }


    /**
     * 用来得到真实行数
     * @param sheet 需要读取的Excel表格（excel文件的工作簿的名称）
     * @return
     *
     */
    public static int readExcelValueRows(Sheet sheet) {
        int realRow = 0;// 返回的真实行数
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            //i从1开始，不判断第一行标题行
            Row row = sheet.getRow(i);
            if (row == null){
                continue;
            }
            for (Cell cell : row) {
                if (cell == null){
                    continue;
                }
                String value = getValue(cell).trim();
                if (value == null || "".equals(value)){
                    continue;
                } else{
                    realRow++;
                    break;
                }
            }
        }
        return realRow;
    }

    /**
     * 获取excel表格的真实行数
     * @param sheet excel文件的工作簿的名称
     * @return
     */
    public static int getExcelRealRow(Sheet sheet) {
        boolean flag = false;
        for (int i = 1; i <= sheet.getLastRowNum(); ) {
            Row r = sheet.getRow(i);
            if (r == null) {
                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                continue;
            }
            flag = false;
            for (Cell c : r) {
                if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                i++;
                continue;
            } else {
                // 如果是空白行（即可能没有数据，但是有一定格式）
                if (i == sheet.getLastRowNum())// 如果到了最后一行，直接将那一行remove掉
                    sheet.removeRow(r);
                else//如果还没到最后一行，则数据往上移一行
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
            }
        }
        return sheet.getLastRowNum();
    }

}
