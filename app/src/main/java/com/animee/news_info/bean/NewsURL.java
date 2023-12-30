package com.animee.news_info.bean;

public class NewsURL {
//    公共的key   http://v.juhe.cn/toutiao/index?key=0af60a86bfa3575d53c1491d1be310ca&type=top
     public static String key = "0af60a86bfa3575d53c1491d1be310ca";
    public static String info_url = "http://v.juhe.cn/toutiao/index?key="+key+"&type=";
//     头条
     public static String headline_url = info_url +"top";
//    社会
     public static String society_url = info_url +"shehui";
//    国内
     public static String home_url = info_url +"guonei";
//    国际
    public static String internation_url = info_url+"guoji";
//    娱乐
    public static String entertainment_url = info_url+"yule";
//    体育
    public static String sport_url = info_url+"tiyu";
//    军事
    public static String military_url = info_url+"junshi";
//    科技
    public static String science_url = info_url+"keji";
//    财经
    public static String finance_url = info_url+"caijing";
//    时尚
    public static String fashion_url = info_url+"shishang";


}
