package com.zzlzd.android.educloud.wxshare;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zzlzd.android.educloud.R;

public class WeiXinActivity extends AppCompatActivity {
    private Button btn,btn_text,btn_image,btn_url;
    final Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    WxShareAndLoginUtils.WxUrlShare(WeiXinActivity.this,"http://www.baidu.com", "百度", "百度一下",
                            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E4%BA%8C%E7%BB%B4%E7%A0%81&step_word=&hs=2&pn=45&spn=0&di=110409498430&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1307536875%2C564142981&os=1734925767%2C4199470345&simid=4202605727%2C830104856&adpicid=0&lpn=0&ln=1691&fr=&fmq=1545644203273_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg1.ali213.net%2Fshouyou%2Fcutpics%2Fd%2F95921_3.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3F4_z%26e3Bwstd8n_z%26e3BgjpAzdH3ForAzdH3Fba0n9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=",
                            WxShareAndLoginUtils.WECHAT_FRIEND);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        btn = (Button) findViewById(R.id.btn);
        btn_text = (Button) findViewById(R.id.btn_text);
        btn_image = (Button) findViewById(R.id.btn_image);
        btn_url = (Button) findViewById(R.id.btn_url);
//        测试微信登录
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WxShareAndLoginUtils.WxLogin(WeiXinActivity.this);
            }
        });
        //文字分享
        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WxShareAndLoginUtils.WxTextShare(WeiXinActivity.this,"微信分享",WxShareAndLoginUtils.WECHAT_MOMENT);
            }
        });
        //图片分享
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                WxShareAndLoginUtils.WxBitmapShare(WeiXinActivity.this,bitmap,WxShareAndLoginUtils.WECHAT_MOMENT);
            }
        });
        //链接分享
        btn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHandler.sendEmptyMessage(1);
            }
        });
    }
}
