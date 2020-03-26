package cn.com.gatico.utils;

public class TestMD5 {
    public static void main(String[] args) {
        System.out.println(MD5Utils.encrypt("admin"));
        System.out.println(MD5Utils.encrypt(MD5Utils.encrypt("admin")));
        System.out.println(MD5Utils.SHA1(MD5Utils.encrypt(MD5Utils.encrypt("admin"))));
    }
}
