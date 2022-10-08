package cn.com.gatico.Excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

public class 导入店铺信息 {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\7x-networks\\Downloads\\屈臣氏新增门店(1).xlsx");
        ExcelReader excelReader = reader.setSheet(0);
        for (int i = 1; i < excelReader.getRowCount(); i++) {
            System.out.println(excelReader.readCellValue(1, i));
        }
    }
}
