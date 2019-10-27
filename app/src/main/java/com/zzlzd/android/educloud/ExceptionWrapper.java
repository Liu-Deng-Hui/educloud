package com.zzlzd.android.educloud;


import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ExceptionWrapper {
    private Activity activity;
    private TextView tv;
    private RelativeLayout exceptionLayer;
    private TextView error_msg_tv;
    private Button wait_btn;
    private Button retry_btn;
    private Runnable retry;
    private Runnable waiting;
    private int lastError = ERROR_NONE;

    public final static int ERROR_NONE = -1;
    public final static int SERVER_ERROR = 0;
    public final static int PAGE_TIMEOUT = 1;
    public final static int NET_ERROR = 2;

    public void setRetryCallback(Runnable retry) {
        this.retry = retry;
    }

    public void setRewaitCallback(Runnable waiting) {
        this.waiting = waiting;
    }

    private final String[] errorMessages = new String[] {
        "商城正在升级维护,请稍后重试...",
        "亲，您稍后, 正在拼命加载中....",
        "网络请求失败, 请检查您的网络"
    };
    public ExceptionWrapper(Activity activity) {
        this.activity = activity;
    }

    public void initView() {
        this.tv = activity.findViewById(R.id.djs_tv1);
        this.error_msg_tv = activity.findViewById(R.id.error_msg_tv);
        this.wait_btn = activity.findViewById(R.id.wait_btn);
        wait_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(waiting != null) {
                    waiting.run();
                }
            }
        });
        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(retry != null) {
                    retry.run();
                }
            }
        });
    }


    public void show(int errCode) {
        this.exceptionLayer.setVisibility(View.VISIBLE);
        switch (errCode) {
            case SERVER_ERROR:
                this.lastError = errCode;
                this.error_msg_tv.setText(errorMessages[0]);
                this.retry_btn.setVisibility(View.VISIBLE);
                this.wait_btn.setVisibility(View.GONE);
                break;
            case PAGE_TIMEOUT:
                if(this.lastError == ERROR_NONE) {
                    this.lastError = errCode;
                    this.error_msg_tv.setText(errorMessages[1]);
                    this.retry_btn.setVisibility(View.GONE);
                    this.wait_btn.setVisibility(View.VISIBLE);
                }
                break;
            case NET_ERROR:
                this.lastError = errCode;
                this.error_msg_tv.setText(errorMessages[2]);
                this.retry_btn.setVisibility(View.VISIBLE);
                this.wait_btn.setVisibility(View.GONE);
                break;
        }
    }

    public void hide() {
        this.exceptionLayer.setVisibility(View.GONE);
    }
}
