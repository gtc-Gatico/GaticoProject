package cn.com.gatico.CMD;

import cn.com.gatico.窗体.UserItem;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gatico
 * @version 1.0
 * @date 2020/1/9 18:13
 */
public class SyncCommand {
    public static void main(String[] args) throws Exception {
        File file = new File(SyncCommand.class.getClass().getResource("/").toURI());
        String files[] = file.list((dir, name) -> {
            return name.endsWith("properties");
        });
        for (int i = 0; i < files.length; i++) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file + File.separator + files[i]));
            properties.entrySet().forEach(objectObjectEntry -> {
                System.setProperty(objectObjectEntry.getKey().toString(), objectObjectEntry.getValue().toString());
            });
        }
        Properties properties = System.getProperties();
        System.out.println(properties);

        System.out.println(file.getAbsolutePath());
        System.exit(1);
        String command = "chdir";
        System.out.println(command);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byte[] isArr = null;
        byte[] esArr = null;
        ByteArrayOutputStream isOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream esOutputStream = new ByteArrayOutputStream();
        try {
            Process exec = Runtime.getRuntime().exec("cmd -start", new String[]{command});
            InputStream is = exec.getInputStream();
            InputStream es = exec.getErrorStream();
            new Thread(() -> {
                try {
                    int v1;
                    while ((v1 = is.read()) != -1) {
                        isOutputStream.write(v1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    int v2;
                    while ((v2 = es.read()) != -1) {
                        esOutputStream.write(v2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            while (exec.isAlive()) {
                Thread.sleep(100);
            }

            isArr = isOutputStream.toByteArray();
            esArr = esOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String msg = "";
        String err = "";
        try {
            if (isArr != null && isArr.length >= 0) {
                msg = new String(isArr, "GBK");
            }
            if (esArr != null && esArr.length >= 0) {
                err = new String(esArr, "GBK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(msg);
        System.out.println("---------------------------------");
        System.out.println(err);
    }
}
