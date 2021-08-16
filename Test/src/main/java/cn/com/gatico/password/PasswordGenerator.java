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
        genpwd("7x001201607090168326f", "IrIFwD6YIe0HGJ9BTb5ciQgtBhtY3har");//a9f931f0d9c2e5cc31789a7570193af7
        genpwd("7x00123768854ea29", "2Z1gWy8Ng0MgB4lizUDnpvRDUuiDu8eb");//c0121cf06f636bd1ab7decfcef3f3b03
        genpwd("7x00123768854ea29", "YsMpJlH7kmTnY9uZuAYv975GcnZs0VZT");//c0121cf06f636bd1ab7decfcef3f3b03
        genpwd("7x0012376885a5980", "9e69a410aa48299c980edc9565ecb693");//9e69a410aa48299c980edc9565ecb693
        genpwd("7x0012016081001efd91b", "7c3cd93cb8b0b2757be7cd62865806c3");//7c3cd93cb8b0b2757be7cd62865806c3  node 7c3cd93cb8b0b2757be7cd62865806c3
        genpwd("7x00120140401010ae04e", "493df827c4583515958ca61fa2b3dc06");//493df827c4583515958ca61fa2b3dc06
        genpwd("prism_test", "prism_test");//
        genpwd("prism_hz_test", "prism_hz_test");//
        genpwd("tempest_watsons_test", "tempest_watsons_test");//
        genpwd("7x00120191104011a1b4e", "7x00120191104011a1b4e");//c21ffd4c43ff29d01baf013b3c2e6f49
        genpwd("7x00123768853b7c9", "7x00123768853b7c9");//
        genpwd("tempest_hz_test253", "c1EsVew5WEAwO7c31FYceMVKU1WQPrU6");//
        genpwd("prism_hz_test253", "widWgr6Rc7zBh0KO3JQCGdQgcs2qvGv2");//
        genpwd("commom_hztest_collector", "0rXsiDFmK6FMN0PdNyMkFfIB7e2VZJxY");//
        genpwd("admin", "7x-networks");//
        genpwd("7x001201607090168326f", "d51829ff2a543884a96a043e116ce0fa");//856158b237f972e7a4aa157f3e3fe5cb
        genpwd("tempest_hz_test241", "3BuoLPXu54SZvCsMHvJFAFmIxPKslMpB");//
        genpwd("prism_hz_test241", "Q8XE95nPDAUpVc30caDxYTVhDmzGUgZc");//
        genpwd("new_commom_hztest_collector", "tByz4nYm3FRvItrJWFWBa8d1YhXNHukl");//
        genpwd("tempest_syzj", "IKjxsb36Ys9GJ83VHAE7rLgLRQIIa60X");//
        genpwd("prism_syzj", "KgAWHmpaQYZrDfWnxFtCCAUG53iIE33b");//
        genpwd("commom_syzj_collector", "bmjyKB1ipu4jPkKYbfHRwrF0BQZ0vVpz");//
        genpwd("7x0012376885a5b81", "0311be19b753a459ac2524c56e8ee87b");//
        genpwd("sanyizhongji", "31Zhongji@31Zhongji!");//
        genpwd("7x0012376885a5a5e", "551917f115fefee170965f6aa2b2bff0");//
        genpwd("7x00120200303011c1d22", "4b78238504421b7bdb535a1c9b217c68");//
        genpwd("zhenkunhang", "Zhenkunhang@zhenkunhang7");//
        genpwd("prism_Opstest1", "prism_Opstest1@zhenkunhang7");//
        genpwd("tempest_Opstest1", "tempest_Opstest1@zhenkunhang7");//
        genpwd("common_Opstest1_collector", "common_Opstest1_collector");//
        genpwd("prism_watson_test", "prism_watson_test");//
        genpwd("prism_watson_test2", "prism_watson_test1");//
        genpwd("tempest_watsons_test", "tempest_watsons_test");//
        genpwd("prism_zkhqp", "NGe3C05qV6NZSMJIy9YDOGsRwtvFB3vw");//
        genpwd("tempest_zkhqp", "o8la2pmnE0QHu4r7dIUaXfQfhjOh0cmJ");//
        genpwd("commom_zkhqp_collector", "vHyJ08ycBPHFNNCr0CAmoZYhRgHP89oI");//
        genpwd("7x00120200303011a361e", "1e4c29a7d3a330dd03534a51c1a7b6d8");//
        genpwd("7x00120190510010fa91e", "yxEpm5kuMa3WRRbwAEG1E3GWWfV255a3");//
        genpwd("7x001201810100110b8e2", "hqZ7zXmweXFTPXUlSv5D8D7VS1XP3XqT");//
        genpwd("7x001201810100110b8e2", "hqZ7zXmweXFTPXUlSv5D8D7VS1XP3XqT");//
        genpwd("7x0012376885a5b2d", "55c77b530d07be4aa202adb07ab9537e");//
        genpwd("7x00120200303011a0c3c", "469de53712df36386b629c52110a1824");//
        genpwd("7x00120200303011a0c3c", "ff9c8435dce4660a6c73332ef3948b15");//
        genpwd("check_point", "yjgsYzlbr1GBeYyTSHBUQSC7Lb9o5wHL");//
        genpwd("tempest_rongjie", "C5T26vVHeCPnnWwCg4zHXrqFfaJ78hsZ");//
        genpwd("prism_rongjie", "6CA4AmfsymeBm6MFIMY3MnomZjPAm3Sp");//
        genpwd("commom_rongjie_collector", "wEAtPWfG7DQ3Y1bhzTT57H8k27QQMw1g");//
        genpwd("zhenkunhang", "Zhenkunhang@zhenkunhang7");//
        genpwd("7x00120200303011a352e", "4c792aec4324dab28dc7d486c762450a");//89249d675cfafaf1c1998df013a0877a
        genpwd("prism_jollibee", "ZTPI2cyXuELMHA5iJCvzJyHHeS6mqj3l");//
        genpwd("commom_jollibee_collector", "VR5Gt4lsqjLMK9gtzbQCs3IYsKGhgqYT");//
        genpwd("tempest_jollibee", "wJRLXKY78m7XH1z2KzHVVZNAP5vd6ic8");//
        genpwd("yuantong", "Yuantong@yuantong7");//
        genpwd("yuantong", "Yuantong@yuantong7");//
        genpwd("yuantong", "Yuantong@yuantong7");//

        //序列号，原始云盒密码，数据库密码
        genpwd("7x00120200303011c24c6", "425a0b8e9c60ef3c84f19993c17da420");//11876656676752b2edafcac73671b0b5
        genpwd("7x0012376885a596b", "b9aafd54df0574e3216dc155cafbdfd3");//fc6d84db85a7ea0ea3c581480e70547a

    }

    public static void genpwd(String username, String password) {
        System.out.println(username + ":" + MD5Utils.encrypt(MD5Utils.encrypt(password)) + ":" +
                MD5Utils.encrypt(MD5Utils.encrypt(MD5Utils.encrypt(password)) + "{" + username + "}")
        );
    }

}