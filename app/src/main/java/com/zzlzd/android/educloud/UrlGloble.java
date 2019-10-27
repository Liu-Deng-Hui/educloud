package com.zzlzd.android.educloud;

public class UrlGloble {

    public static String HOMEURL ="http://scm.newfw.qibaliu.com/";//地址带/
    //public static String HOMEURL ="http://192.168.0.157:8803/";//地址带/
    public static String OrderPayURL = HOMEURL+"order_pay_app";
    public static String url=HOMEURL+"order_pay_app";
    public static String paySucUrl=HOMEURL+"wx_pay_success?type=goods&id=";
    public static String QRCODE = HOMEURL + "buyer/myQrcod";
    public static String GET_SHAREINFO = HOMEURL + "getShareInfo";
    public static String SEARCH = HOMEURL +"search";
    public static String GOODS_CART1 = HOMEURL +"goods_cart1";
    public static String BUYER_ORDER_LIST = HOMEURL +"buyer/order_list";
    public static String BUYER_CENTER = HOMEURL +"buyer/center";
    public static String HOME_INDEX = HOMEURL +"index";
    public static String HOME_PATH = "/";
    public static String HOME_INDEX_PATH = "/index";

    //版本更新接口地址
    public static String UPDATE_URL = "http://scm.newfw.qibaliu.com/appInfo";
    public static String APP_KEY = "1";
}

