package cn.com.gatico.TestWord;

import org.convert.PDFConvert;

public class Test {
    public static void main(String[] args) throws Exception {
        PDFConvert pdfConvert = new PDFConvert();
        /*String fileName = "分发和操作文档.docx";
        Process exec = Runtime.getRuntime().exec("explorer.exe C:\\Users\\48909\\Desktop\\" + fileName);
        System.out.println(fileName);
        File pdf = new File("F:\\库\\文档\\" + fileName.split("\\.")[0] + ".pdf");
        System.out.println(pdf.getName());
        boolean flag = true;
        while (flag) {
            if (pdf.exists()) {
                flag = false;
                for (int i = 0; i < 10; i++) {
                    Runtime.getRuntime().exec("TASKKILL /IM WINWORD.EXE");
                    Thread.sleep(100L);
                }
                System.exit(0);
            } else {
                Thread.sleep(200L);
            }
        }*/
    }
}
