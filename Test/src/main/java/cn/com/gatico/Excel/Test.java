package cn.com.gatico.Excel;

import cn.com.gatico.utils.ArrayUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        Map<String, List<String>> imeiMap = new HashMap<>();
        Map<String, List<String>> idMap = new HashMap<>();

        String path = "C:\\Users\\48909\\Desktop\\新红茶模组(包含重复).xlsx";
        XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream(path));
        XSSFSheet sheet1 = sheets.getSheetAt(0);
        XSSFSheet sheet2 = sheets.getSheetAt(1);
        Iterator<Row> row1Iterator = sheet1.rowIterator();
        Row row1 = row1Iterator.next();
        System.out.format("%s,%s,%s", row1.getCell(0).getStringCellValue(),
                row1.getCell(1).getStringCellValue(),
                row1.getCell(2).getStringCellValue(),
                row1.getCell(3).getStringCellValue()
        );
        while (row1Iterator.hasNext()) {
            Row next = row1Iterator.next();
            Cell cell = next.getCell(0);
            String remove = cell == null ? "0" : cell.getStringCellValue();
            if (!"1".equals(remove)) {
                List<String> list = new LinkedList<>();
                for (int i = 1; i < next.getLastCellNum(); i++) {
                    list.add(String.valueOf(next.getCell(i) == null ? "" : next.getCell(i).getStringCellValue()));
                }
                imeiMap.put(list.get(list.size() - 1), list);
            }
        }

        Iterator<Row> row2Iterator = sheet2.iterator();
        Row row2 = row2Iterator.next();
        System.out.format("%s,%s,%s", row2.getCell(0).getStringCellValue(),
                row2.getCell(1).getStringCellValue(),
                row2.getCell(2).getStringCellValue(),
                row2.getCell(3).getStringCellValue()
        );
        while (row2Iterator.hasNext()) {
            Row next = row2Iterator.next();
            Cell cell = next.getCell(0);
            String remove = cell == null ? "0" : cell.getStringCellValue();
            if (!"1".equals(remove)) {
                List<String> list = new LinkedList<>();
                for (int i = 1; i < next.getLastCellNum(); i++) {
                    list.add(next.getCell(i) == null ? "" : next.getCell(i).getStringCellValue());
                }
                idMap.put(list.get(list.size() - 2), list);
            }
        }
        JSONArray objects = new JSONArray();
        Collection<?> intersection = ArrayUtils.intersection(idMap.keySet(), imeiMap.keySet());
        intersection.forEach(obj -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imei", imeiMap.get(obj).get(0));
            jsonObject.put("sn", imeiMap.get(obj).get(2));
            jsonObject.put("id", idMap.get(obj).get(0));
            jsonObject.put("userId", idMap.get(obj).get(2));
            objects.add(jsonObject);
        });

        FileWriter fileWriter = new FileWriter(new File("D:\\tmp\\redteaData.json"));
        fileWriter.write(objects.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}
