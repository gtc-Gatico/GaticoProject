package cn.com.gatico.password;

import cn.com.gatico.utils.MD5Utils;

public class PasswordGenerator {
    public static void main(String[] args) {

        genpwd("test2", "test2");
    }

    public static void genpwd(String username, String password) {
        System.out.println(username + ":" + MD5Utils.encrypt(MD5Utils.encrypt(password)) + ":" +
                MD5Utils.encrypt(
                        MD5Utils.encrypt(MD5Utils.encrypt(password)) + "{" + username + "}")
        );
    }

    public static void genpwd(String username) {
        genpwd(username, username);
    }
}