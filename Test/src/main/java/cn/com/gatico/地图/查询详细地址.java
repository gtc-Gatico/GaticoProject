package cn.com.gatico.地图;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashSet;

public class 查询详细地址 {
    public static void main(String[] args) {
        String path = "C:\\Users\\7x-networks\\Downloads\\屈臣氏新增门店1.xlsx";
        ExcelReader reader = ExcelUtil.getReader(path);
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\7x-networks\\Downloads\\屈臣氏新增门店2.xlsx");
        writer.setSheet(0);
        int rowCount = reader.getRowCount();
        for (int i = 1; i < rowCount; i++) {
            System.out.println(i);
            //第一步
/*            String addr = reader.getCell(5, i).getStringCellValue();
            String[] address = getAddress(addr);
            writer.writeCellValue(6,i,address[0]);
            writer.writeCellValue(7,i,address[1]);
            writer.writeCellValue(8,i,address[2]);*/
            //第二步
            String addr = reader.getCell(5, i).toString();
            String provance = reader.getCell(6, i).toString();
            String city = reader.getCell(7, i).toString();
            String district = reader.getCell(8, i).toString();
            if (district != null && addr.indexOf(district) > 0) {
                addr = addr.substring(addr.indexOf(district) + district.length());
                writer.getCell(9, i, true).setCellValue(addr);
                continue;
            }
            if (city != null && addr.indexOf(city) > 0) {
                addr = addr.substring(addr.indexOf(city) + city.length());
                writer.getCell(9, i, true).setCellValue(addr);
                continue;
            }
            if (provance != null && addr.indexOf(provance) > 0) {
                addr = addr.substring(addr.indexOf(provance) + provance.length());
                writer.getCell(9, i, true).setCellValue(addr);
                continue;
            }
//            if (district != null) {
//                if (district.endsWith("市")) {
//                    district = district.replace("市", "");
//                }
//                if (district.endsWith("县")) {
//                    district = district.replace("县", "");
//                }
//                if (district.endsWith("区")) {
//                    district = district.replace("区", "");
//                }
//                addr = addr.substring(addr.indexOf(district) + district.length());
//                writer.getCell(10, i, true).setCellValue(addr);
//                continue;
//            }
//            if (city != null) {
//                if (city.endsWith("市")) {
//                    city = city.replace("市", "");
//                }
//                addr = addr.substring(addr.indexOf(city) + city.length());
//                writer.getCell(10, i, true).setCellValue(addr);
//                continue;
//            }
//            if (provance != null) {
//                if (provance.endsWith("省")) {
//                    provance = provance.replace("省", "");
//                }
//                if (provance.endsWith("市")) {
//                    provance = provance.replace("市", "");
//                }
//                addr = addr.substring(addr.indexOf(provance) + provance.length());
//                writer.getCell(10, i, true).setCellValue(addr);
//                continue;
//            }
            writer.flush();
        }
        writer.flush();
        writer.close();
    }

    public static String[] getAddress(String addr) {
        try {
            String url = "http://restapi.amap.com/v3/geocode/regeo?location=%s,%s&key=0c8be3011cbc3c0a39697e5220fb7529&radius=10";//坐标转换
            url = "http://restapi.amap.com/v3/geocode/geo?address=%s&key=0c8be3011cbc3c0a39697e5220fb7529&radius=10";//搜索
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(String.format(url, addr)).openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder stringBuffer = new StringBuilder();
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuffer.append(s);
            }
            JSONObject parse = (JSONObject) JSONObject.parse(stringBuffer.toString());
            JSONObject geocodes = parse.getJSONArray("geocodes").getJSONObject(0);
            String province = geocodes.getString("province");
            String city = geocodes.getString("city");
            String district = geocodes.getString("district");
            System.out.println(parse);
            return new String[]{province, city, district};
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]{"", "", ""};
    }
}
