package cn.com.gatico;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Iterator;

@SpringBootApplication
@EnableScheduling
public class ApplicationExcel {
    public static void main(String[] args) throws IOException, InvalidFormatException {
//        Paths.get("D:\\data\\test\\newFile.txt",args);
        //       SpringApplication.run(ApplicationExcel.class, args);
//        ss();

        Long a = new Long(-10L);
        Long b = new Long(10L);
        System.out.println(a==b);

        Long c = new Long(-1000L);
        long d = -1000L;
        System.out.println(c==d);

    }

    public static void ss() throws IOException, InvalidFormatException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(new XSSFWorkbook(Paths.get("C:\\Users\\7x-networks\\Downloads\\dpi_application_20220920111737.xlsx").toFile()));
        XSSFWorkbook xssfWorkbook = workbook.getXSSFWorkbook();


        Row row = null;

        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            Cell applicationNameCell = row.getCell(0);
            Cell dpiKeyCell = row.getCell(1);
            Cell markValueCell = row.getCell(2);
            Cell applicationGroupCell = row.getCell(3);
            Cell versionCell = row.getCell(4);
            System.out.print(applicationNameCell.getStringCellValue() + "\t");
            System.out.print(dpiKeyCell.getStringCellValue() + "\t");
            System.out.print((int) markValueCell.getNumericCellValue() + "\t");
            System.out.print(applicationGroupCell.getStringCellValue() + "\t");
            System.out.print(versionCell.getStringCellValue() + "\t");
            System.out.println();
        }
    }
}
