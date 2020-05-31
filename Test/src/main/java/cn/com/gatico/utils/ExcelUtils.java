package cn.com.gatico.utils;

import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /**
     * @param response
     * @param headItem
     * @param dataList
     * @param fileName
     * @throws Exception
     */
    public void exportExcel(HttpServletResponse response, String[] headItem, List<Map<Integer, String>> dataList, String fileName) throws Exception {
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

    private HSSFCellStyle getHeadStyle(HSSFWorkbook wb) {
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

    private HSSFCellStyle getTableStyle(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
        // style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        return style;
    }
}
