package cn.com.gatico;

import java.io.File;

public class fileUpload {
    static FileClient fc = new FileClient();

    public static void main(String[] args) {
        fileUpload fileUpload = new fileUpload();
        if (args.length <= 0) {
            System.out.println("参数不正确。");
            return;
        }
        String address = args[0];
        String port = args[1];
        String path = args[2];
        fc.setAddress(address);
        fc.setPort(Integer.parseInt(port));
        File file = new File(path);
        System.out.println(fileUpload.upload(file));
    }

    public boolean upload(File file) {
        return fc.uploadFile(file);
    }
}
