package com.xiongmeng.android.utils;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class Constants {
    //公网
    //public static String BASE_URL = "http://182.92.5.3:8081/android/resources/";
    public static String BASE_URL = "http://47.93.118.241:8081//android/resources";


    private static final String BASE_URL_JSON = BASE_URL+"/json/";
    /**
     * 主页面的路径
     */
    public static String HOME_URL  = BASE_URL_JSON+"HOME_URL.json";
    /**
     * 图片的基本路径
     */
    public static String BASE_URL_IMAGE  = BASE_URL+"/img";
    /**
     * 网络视频路径
     */
    //public static final String NET_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    public static final String NET_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    public static final String NET_AUDIO_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
}
