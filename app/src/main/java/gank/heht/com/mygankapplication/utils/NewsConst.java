package gank.heht.com.mygankapplication.utils;

/**
 * 新闻常量
 */
public final class NewsConst {

    // 域名
    public static final String NEWS_HOST = "http://c.m.163.com/";
    //专题
    public static  final  String NEWS_SPECIAL = "http://c.m.163.com/nc/special/S1451880983492.html";
    //新闻详情
    public static  final  String NEWS_DETAIL = "http://c.m.163.com/nc/article/{0}/full.html";
    //图片列表
    public static  final  String NEWS_PICS = "http://c.m.163.com/photo/api/list/0096/4GJ60096.json";
    //视频列表
    public static  final  String NEWS_VIDEOS = "http://c.m.163.com/nc/video/list/{0}/n/{1}0-10.html";



    private NewsConst() {
        throw new RuntimeException("NewsConst cannot be initialized!");
    }

}
