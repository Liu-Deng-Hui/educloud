package com.zzlzd.android.educloud.util;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * @Auther: Administrator
 * @Date: 2019/1/16 16:55
 * @Description:
 */
public class JSInterface {
    private Context context;
    private WebView webView;


    public JSInterface(Context context,WebView webView){
        this.context = context;
        this.webView = webView;
    }
    public JSInterface(WebView webView){
        this.webView = webView;
    }

    /**
     * 注意这里的@JavascriptInterface注解， target是4.2以上都需要添加这个注解，否则无法调用
     * @param text
     */
    @JavascriptInterface
    public void showToast(String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void showJsText(String text){
        webView.loadUrl("javascript:jsText('"+text+"')");
    }

    //private String loadUrl= UrlGloble.RESTFUL.replace("restful/cloud/cabinet/","")+"server/client/menu";
    /**
     * 打开一个新页面
     * @param url
     */
    @JavascriptInterface
    public void openView(final String url){
       /* url=Urllink.RESTFUL.replace("restful/cloud/cabinet/","")+url;
        String hybh = MyApp.getInstance().getMem_id();
        String appKey =  PlusCut.getInstance().plus("cloudcab_app");
        if (url.contains("?")){
            loadUrl = url +"&hybh="+hybh+"&appKey="+appKey+"&isClient=true";
        }else{
            loadUrl = url +"?hybh="+hybh+"&appKey="+appKey+"&isClient=true";
        }*/

        Log.e("openView",url);


        webView.post(new Runnable() {
            public void run() {
                webView.loadUrl(url);
            }
        });
    }

    /**
     * 返回上一页面
     */
    @JavascriptInterface
    public void goback(){
       /* Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("viewId",1);
        context.startActivity(intent);
        ((WebViewActivity)context).finish();*/
    }
}
