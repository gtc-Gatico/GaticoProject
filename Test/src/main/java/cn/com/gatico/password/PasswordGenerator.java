package cn.com.gatico.password;

import cn.com.gatico.utils.MD5Utils;

public class PasswordGenerator {
    public static void main(String[] args) {
        System.out.println("old");
        genpwd("nexus", "6S590hImkvANkYui5M6Uy2F4cZhnOr6F");
        genpwd("nexus-watsons", "stZtunxorDUyDaTp3lALoEFbiK9bIqde");
        genpwd("nexus-watsons-ha", "6Kn5WmzGKQCnYENAyizOKR0ieopeaOIv");
        genpwd("stormwind", "2DyVzC2XQso7rjGxZTI7M7WdWbm0E7DT");
        System.out.println("new");
        genpwd("nexus", "CArh1iymXEhhDqkg13QaOV2zzA12g4Qb");
        genpwd("nexus-watsons", "PzUlUK2leKcamk1rlg8jLjspLQPIvU5m");
        genpwd("nexus-watsons-ha", "Gv8VMKJPXSa7lEHOP3gblf0IDEH99jl1");
        genpwd("stormwind", "HNBqN8OG3pWit7Kz1RWzrHgfmC56urOn");
        System.out.println("----");
        genpwd("7xops", "7xops@7xops!");
        genpwd("watson", "watson@watson!");
        genpwd("7x-networks", "7x-networks");
        genpwd("watsons", "W@ts0ns2018");
        genpwd("test", "test");
    }

    public static void genpwd(String username, String password) {
        System.out.println(username + ":" + MD5Utils.encrypt(MD5Utils.encrypt(password)) + ":" +
                MD5Utils.encrypt(MD5Utils.encrypt(MD5Utils.encrypt(password)) + "{" + username + "}")
        );
    }

}