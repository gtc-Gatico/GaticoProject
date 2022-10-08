package cn.com.gatico.Excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class 处理店铺设备 {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\7x-networks\\Downloads\\屈臣氏店铺详细信息表5.xlsx");
        ExcelReader excelReader = reader.setSheet(0);
        Map<String, String> storeNo = new HashMap<>();
        for (int i = 1; i < excelReader.getRowCount(); i++) {
            storeNo.put(excelReader.readCellValue(1, i).toString(), excelReader.readCellValue(14, i).toString());
        }
        System.out.println(storeNo);
        excelReader = reader.setSheet(1);
        String no;
        String wan;
        Map<String, device> deviceMap = new HashMap<>();
        Map<String, List<String>> networksMap = new HashMap<>();
        for (int i = 1; i < excelReader.getRowCount(); i++) {
            no = excelReader.readCellValue(0, i).toString();
            device device = deviceMap.getOrDefault(no, new device());
            device.no = no;
            device.name = excelReader.readCellValue(6, i).toString();
            device.alias = excelReader.readCellValue(7, i).toString();
            wan = excelReader.readCellValue(8, i).toString();
            device.wan = wan;
            List<String> networks = networksMap.getOrDefault(no, new ArrayList<>());
            networks.add(wan);
            networksMap.put(no, networks);
            if (wan.equals("wan1")) {
                Object o = excelReader.readCellValue(9, i);
                if (Objects.nonNull(o)) {
                    device.tx1 = excelReader.readCellValue(9, i).toString();
                    if (device.tx1.contains("Mbps")) {
                        device.tx1 = device.tx1.replace("Mbps", "");
                    }
                    if (device.tx1.contains("Kbps")) {
                        device.tx1 = String.valueOf(Double.parseDouble(device.tx1.replace("Kbps", "")) / 1000);
                    }
                    device.rx1 = excelReader.readCellValue(10, i).toString();
                    if (device.rx1.contains("Mbps")) {
                        device.rx1 = device.rx1.replace("Mbps", "");
                    }
                    if (device.rx1.contains("Kbps")) {
                        device.rx1 = String.valueOf(Double.parseDouble(device.rx1.replace("Kbps", "")) / 1000);
                    }
                }
            } else if (wan.equals("wan2")) {
                Object o = excelReader.readCellValue(9, i);
                if (Objects.nonNull(o)) {
                    device.tx2 = excelReader.readCellValue(9, i).toString();
                    if (device.tx2.contains("Mbps")) {
                        device.tx2 = device.tx2.replace("Mbps", "");
                    }
                    if (device.tx2.contains("Kbps")) {
                        device.tx2 = String.valueOf(Double.parseDouble(device.tx2.replace("Kbps", "")) / 1000);
                    }
                    device.rx2 = excelReader.readCellValue(10, i).toString();
                    if (device.rx2.contains("Mbps")) {
                        device.rx2 = device.rx2.replace("Mbps", "");
                    }
                    if (device.rx2.contains("Kbps")) {
                        device.rx2 = String.valueOf(Double.parseDouble(device.rx2.replace("Kbps", "")) / 1000);
                    }
                }
            }
            deviceMap.put(no, device);
        }
        AtomicInteger i = new AtomicInteger(1);
        try {
            BufferedWriter store_device = new BufferedWriter(new FileWriter("D:\\store_device.sql"));
            BufferedWriter store_store_device = new BufferedWriter(new FileWriter("D:\\store_store_device.sql"));
            BufferedWriter store_networks = new BufferedWriter(new FileWriter("D:\\store_networks.sql"));
            deviceMap.values().forEach((devices) -> {
                try {
                    store_device.write("insert into store_device(id,name,alias,sn,category,wan1_rx_bandwidth,wan1_tx_bandwidth,wan2_rx_bandwidth,wan2_tx_bandwidth,create_time)values(" +
                            i.get() + ",'" +
                            devices.name + "','" +
                            devices.alias + "','" +
                            devices.alias + "','" +
                            "box" + "'," +
                            (devices.rx1 != null ? Double.parseDouble(devices.rx1) * 1000 * 1000 : null) + "," +
                            (devices.tx1 != null ? Double.parseDouble(devices.tx1) * 1000 * 1000 : null) + "," +
                            (devices.rx2 != null ? Double.parseDouble(devices.rx2) * 1000 * 1000 : null) + "," +
                            (devices.tx2 != null ? Double.parseDouble(devices.tx2) * 1000 * 1000 : null) + "," +
                            "'2021-08-25 14:00:00'" +
                            ");\n");
                    store_store_device.write("insert into `store-store_device`(store_id,store_device_id)values(" + storeNo.get(devices.no) + "," + (i.get()) + ");\n");
                    List<String> strings = networksMap.get(devices.no);
                    if (strings != null) {
                        strings.forEach(s -> {
                            try {
                                store_networks.write("insert into store_networks(store_id,store_device_id,interface_name,create_time)values(" +
                                        storeNo.get(devices.no) + "," +
                                        (i.get()) + "," +
                                        s.toUpperCase() + "," +
                                        "'2021-08-25 14:00:00'" +
                                        ");\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    System.out.println(i.getAndAdd(1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            store_device.flush();
            store_store_device.flush();
            store_networks.flush();
            store_device.close();
            store_store_device.close();
            store_networks.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class device {
    String no;
    String name;
    String alias;
    String wan;
    String tx1;//上行
    String rx1;//下行
    String tx2;//上行
    String rx2;//下行
}
