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
        genpwd("admin", "admin");
        genpwd("BPCC", "BPCC@BPCC!");
        genpwd("BPPC", "BPPC@BPPC!");//BPCC  4ab5e290a3fe63cd64095a082add1cac
        genpwd("zhongjian-", "zhongjian@zhongjian!");//zhongjian-   f06fc23c8b76264633eb76902a1331f3
        genpwd("zhongjian", "zhongjian@zhongjian!");
        genpwd("xunkun.cai", "woaikunkun");
        genpwd("kaiyu.rao", "123456");
        System.out.println("----------------------");
        genpwd("greenland", "greenland@greenland!");//a9f931f0d9c2e5cc31789a7570193af7
    }

    public static void genpwd(String username, String password) {
        System.out.println(username + ":" + MD5Utils.encrypt(MD5Utils.encrypt(password)) + ":" +
                MD5Utils.encrypt(MD5Utils.encrypt(MD5Utils.encrypt(password)) + "{" + username + "}")
        );
    }

}