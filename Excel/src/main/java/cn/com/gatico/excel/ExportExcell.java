package cn.com.gatico.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ExportExcell {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Date online = new Date(
                new Date(2019, 4, 9, 18, 00, 00).getTime()
                        - new Date(2019, 4, 8, 18, 00, 00).getTime());
        //0:11:43
        int ss = (int) (online.getTime() / 1000) % 60;
        int mm = (int) (online.getTime() / 1000 / 60) % 60;
        int hh = (int) (online.getTime() / 1000 / 60 / 60) % 24;
        int dd = (int) (online.getTime() / 1000 / 60 / 60 / 24);
        System.out.println(dd + " " + hh + ":" + mm + ":" + ss);
        online = new Date(online.getTime() - TimeUnit.HOURS.toMillis(8));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(online));

       /* Instant sinout = Instant.parse("2019-04-09 18:23:26");  //当前的时间
        System.out.println("Inst1：" + sinout);
        Instant sinin = Instant.parse("2019-04-09 18:11:43");     //当前时间+10秒后的时间
        System.out.println("Inst2：" + sinin);

        System.out.println("以毫秒计的时间差：" + Duration.between(sinout, sinin).toString());*/
        int size = 10000;
        new Thread(() -> {
            long begin = System.currentTimeMillis();
            System.out.println("1开始：" + sdf.format(begin));
            createExcel1(size);
            long end = System.currentTimeMillis();
            System.out.println("1结束：" + sdf.format(end));
            System.out.println("1用时：" + (end - begin) / 1000 + "秒");
        }).start();
        new Thread(() -> {
            long begin = System.currentTimeMillis();
            System.out.println("2开始：" + sdf.format(begin));
            createExcel2(size);
            long end = System.currentTimeMillis();
            System.out.println("2结束：" + sdf.format(end));
            System.out.println("2用时：" + (end - begin) / 1000 + "秒");
        }).start();
    }


    public static void createExcel1(int size) {
        //创建HSSFWorkbook对象(excel的文档对象)
        SXSSFWorkbook wkb = new SXSSFWorkbook(100);
        //建立新的sheet对象（excel的表单）
        SXSSFSheet sheet = wkb.createSheet("worksheet");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        SXSSFRow row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        SXSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("用户使用记录报表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //在sheet里创建第二行
        SXSSFRow row2 = sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("时间范围:");
        row2.createCell(1).setCellValue(sdf.format(new Date()) + "-" + sdf.format(new Date()));
        //在sheet里创建第三行
        SXSSFRow row3 = sheet.createRow(2);
        SXSSFRow row4 = sheet.createRow(3);
        row4.createCell(0).setCellValue("序号");
        row4.createCell(1).setCellValue("物理地址");
        row4.createCell(2).setCellValue("手机号码");
        row4.createCell(3).setCellValue("微信openId");
        row4.createCell(4).setCellValue("认证途径");
        row4.createCell(5).setCellValue("上线时间");
        row4.createCell(6).setCellValue("下线时间");
        row4.createCell(7).setCellValue("在线时长");
        //.....省略部分代码
        SXSSFRow rows;
        for (int i = 4, j = 1; i <= size; i++, j++) {
            rows = sheet.createRow(i);
            rows.createCell(0).setCellValue(j);
            rows.createCell(1).setCellValue(UUID.randomUUID().toString());
            rows.createCell(2).setCellValue("1" + Math.random() * 100 + "" + Math.random() * 1000 + "" + Math.random());
            rows.createCell(3).setCellValue(UUID.randomUUID().toString());
            rows.createCell(4).setCellValue(UUID.randomUUID().toString());
            rows.createCell(5).setCellValue(sdf.format(new Date()));
            rows.createCell(6).setCellValue(sdf.format(new Date()));
            rows.createCell(7).setCellValue(sdf.format(new Date()));
        }

        try {
            File file = new File("/home/tianci.gao/test/test1_" + size + ".xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            wkb.write(fos);
            fos.flush();
            fos.close();
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //输出Excel文件
        /*OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        wkb.write(output);
        output.close();*/
    }

    public static void createExcel2(int size) {
        //创建HSSFWorkbook对象(excel的文档对象)
        Workbook wkb = null;
        try {
            wkb = WorkbookFactory.create(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //建立新的sheet对象（excel的表单）
        Sheet sheet = wkb.createSheet("worksheet");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        Row row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        Cell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("用户使用记录报表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        //在sheet里创建第二行
        Row row2 = sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("时间范围:");
        row2.createCell(1).setCellValue(sdf.format(new Date()) + "-" + sdf.format(new Date()));
        //在sheet里创建第三行
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        row4.createCell(0).setCellValue("序号");
        row4.createCell(1).setCellValue("物理地址");
        row4.createCell(2).setCellValue("手机号码");
        row4.createCell(3).setCellValue("微信openId");
        row4.createCell(4).setCellValue("认证途径");
        row4.createCell(5).setCellValue("上线时间");
        row4.createCell(6).setCellValue("下线时间");
        row4.createCell(7).setCellValue("在线时长");
        //.....省略部分代码
        Row rows;
        for (int i = 4, j = 1; i <= size; i++, j++) {
            rows = sheet.createRow(i);
            rows.createCell(0).setCellValue(j);
            rows.createCell(1).setCellValue(UUID.randomUUID().toString());
            rows.createCell(2).setCellValue("1" + Math.random() * 100 + "" + Math.random() * 1000 + "" + Math.random());
            rows.createCell(3).setCellValue(UUID.randomUUID().toString());
            rows.createCell(4).setCellValue(UUID.randomUUID().toString());
            rows.createCell(5).setCellValue(sdf.format(new Date()));
            rows.createCell(6).setCellValue(sdf.format(new Date()));
            rows.createCell(7).setCellValue(sdf.format(new Date()));
        }

        try {
            File file = new File("/home/tianci.gao/test/test2_" + size + ".xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            wkb.write(fos);
            fos.flush();
            fos.close();
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //输出Excel文件
        /*OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        wkb.write(output);
        output.close();*/
    }
}
