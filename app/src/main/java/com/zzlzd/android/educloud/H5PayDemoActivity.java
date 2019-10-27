package com.zzlzd.android.educloud;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.zzlzd.android.educloud.util.UpdateAppHttpUtil;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.listener.ExceptionHandler;

public class H5PayDemoActivity extends AppCompatActivity  {

    private WebView mWebView;
    private IWXAPI api;
    public static String oderid = "";

    private boolean isFirstUse = true;
    private GuideWrapper guider =null;
    private WaitingWrapper webWaiting = null;
    private ExceptionWrapper exceptionWrapper = null;

    /**
     * 获取权限使用的 RequestCode
     */
    private static final int PERMISSIONS_REQUEST_CODE = 1002;
    private String[] permissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        this.guider = new GuideWrapper(this);
        //
        this.isFirstUse = this.getIntent().getBooleanExtra("isFirstUse", false);
        setContentView(R.layout.activity_webview);

        requestPermission();


        overridePendingTransition(0, 0);
        //
        this.guider.initView();
        this.initView();
        //检测版本信息
        checkAppVersion();
   }

    /**
     * 自动更新App版本
     */
    public void checkAppVersion() {
        new UpdateAppManager
                .Builder()
                .setAppKey(UrlGloble.APP_KEY)
                //当前Activity
                .setActivity(this)
                //更新地址
                .setUpdateUrl(UrlGloble.UPDATE_URL)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .update();
    }


    private void requestPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for(String str : permissions){
                if(ContextCompat.checkSelfPermission(this,str) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,permissions,PERMISSIONS_REQUEST_CODE);
                }
            }

        }
    }

    /**
     * 权限获取回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSIONS_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        //如果拒绝权限，给出提示
                        String message = "";
                        switch (permissions[i]) {
                            case Manifest.permission.READ_EXTERNAL_STORAGE:
                                message = "应用无法访问读取权限";
                                break;
                            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                                message = "应用无法访问写入权限";
                                break;
                        }
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }






    //
    public void initView(){

        mWebView = (WebView) findViewById(R.id.webview_web);
        WebSettings settings = mWebView.getSettings();
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(false);
        mWebView.setVerticalScrollbarOverlay(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl("http://m.fztool.com");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }




    private class MyWebViewClient extends WebViewClient {


    }




    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public  static Boolean FROMWX = false;
    @Override
    public void onResume() {
        super.onResume();
        //
        if(FROMWX){
            String url = UrlGloble.paySucUrl + oderid;
            mWebView.loadUrl(url);
            FROMWX = false;
        }
    }

    @Override
    public void onBackPressed() {
        mWebView.setBackgroundColor(ContextCompat.getColor(H5PayDemoActivity.this,android.R.color.transparent));
        mWebView.removeAllViews();
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
           finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    private long exitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            String url=mWebView.getUrl();
            if (UrlGloble.HOME_INDEX.equals(url)){
                if ((System.currentTimeMillis() - exitTime) > 2000)
                {
                    Toast.makeText(getApplicationContext(), "再按一次,退出",Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
                return true;
            }
            if(url.contains(UrlGloble.paySucUrl)){
                mWebView.loadUrl(UrlGloble.BUYER_ORDER_LIST);
                return true;
            }
            if(url.contains(UrlGloble.BUYER_ORDER_LIST)){
                mWebView.loadUrl(UrlGloble.HOME_INDEX);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

