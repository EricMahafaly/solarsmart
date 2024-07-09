package com.solarsmart.frontoffice.services.implementation.base;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Data
public abstract class ExcelServiceAbstract {
    private SXSSFWorkbook workbook;
    private Sheet sheet;

    protected ExcelServiceAbstract(){
        workbook = new SXSSFWorkbook();
    }

    public void writeTableHeaderExcel(String sheetName, String titleName, String[] headers) {

        // sheet
        sheet = workbook.createSheet(sheetName);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        style.setFont(font);

        // header
        Row row = sheet.createRow(1);
        font.setBold(true);
//        font.setFontHeight(16);
        style.setFont(font);
        for (int i = 0; i < headers.length; i++) {
            createCell(row, i, headers[i], style);
        }
    }

    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
//        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof LocalDateTime date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String dateString = date.format(formatter);
            cell.setCellValue(dateString);
        }else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    public CellStyle getFontContentExcel() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        style.setFont(font);
        return style;
    }

    public abstract void writeDataTable();

}
