package cn.com.gatico.utils;

import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ExcelUtils {

    /**
     * @param response
     * @param headItem
     * @param dataList
     * @param fileName
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, String[] headItem, List<Map<Integer, String>> dataList, String fileName) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        Row row1 = sheet.createRow(0);
        HSSFCellStyle headStyle = getHeadStyle(workbook);
        HSSFCellStyle tableStyle = getTableStyle(workbook);
        //创建excel表头
        for (int i = 0; i < headItem.length; i++) {
            sheet.setColumnWidth(i, 5000);
            Cell cell = row1.createCell(i);
            cell.setCellValue(headItem[i]);
            cell.setCellStyle(headStyle);
        }

        //创建内容体
        for (int i = 0; i < dataList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Map<Integer, String> tMap = dataList.get(i);

            for (int j = 0; j < headItem.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(new HSSFRichTextString(tMap.get(j)));
                cell.setCellStyle(tableStyle);
            }
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(5);

        OutputStream os = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename = " + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/vnd.ms-excel;;charset=UTF-8");
        workbook.write(os);

        os.flush();
        os.close();
    }

    public static HSSFCellStyle getHeadStyle(HSSFWorkbook wb) {
        HSSFCellStyle titleStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setUnderline(FontRecord.U_SINGLE);
        font.setColor(IndexedColors.ORANGE.index);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("微软雅黑");
        titleStyle.setFont(font);
        //titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        return titleStyle;
    }

    public static HSSFCellStyle getTableStyle(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
        // style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        return style;
    }

    /**
     *
     * @param title 第一行标题
     * @param header 第二行表头
     * @param data 导出数据
     * @param dataFunction 第三行数据排布方式，T 每行数据，SXSSFRow 行对象
     * @return 文件流数据
     */
    public static <T> byte[] exportExcel(String title, List<String> header, List<T> data, BiConsumer<T, SXSSFRow> dataFunction) {
        try {
            //true创建.xlsx文件 false 创建.xls文件
            SXSSFWorkbook wkb = new SXSSFWorkbook(100);
            //建立新的sheet对象（excel的表单）
            SXSSFSheet sheet = wkb.createSheet("worksheet");
            //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            int rowIndex = 0;
            SXSSFRow row1 = sheet.createRow(rowIndex++);
            //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
            SXSSFCell cell = row1.createCell(0);
            //设置单元格内容
            cell.setCellValue(title);
            CellStyle style = wkb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(style);
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
            //在sheet里创建第二行
            //创建单元格并设置单元格内容
            SXSSFRow row2 = sheet.createRow(rowIndex++);
            for (int i = 0; i < header.size(); i++) {
                row2.createCell(i).setCellValue(header.get(i));
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, row2.getLastCellNum()));

            if (null != data && !data.isEmpty()) {
                SXSSFRow rows;
                for (int j = 0; j < data.size(); j++) {
                    rows = sheet.createRow(rowIndex++);
                    dataFunction.accept(data.get(j), rows);
                }
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            wkb.write(bos);
            bos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
