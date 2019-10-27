package com.zzlzd.android.educloud;

import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class WaitingWrapper {

    private Activity activity;
    private int maxCounter = 10;
    private int timerCounter = 10;
    private TextView tv;
    private RelativeLayout overlayLayer;
    private Button retry_btn;
    private Timer timer = null;
    private String url = null;
    private Runnable timeoutCallback = null;

    public WaitingWrapper(Activity activity, int maxCounter, Runnable timeoutCallback) {
        this.activity = activity;
        this.maxCounter = this.timerCounter = maxCounter;
        this.timeoutCallback = timeoutCallback;
    }

    public void initView() {
        this.tv = activity.findViewById(R.id.djs_tv1);
        this.overlayLayer = activity.findViewById(R.id.overlayLayer);
    }
    private Runnable timerProc = new Runnable() {
        @Override
        public void run() {
            timerCounter--;
            if (timerCounter > 0) {
                tv.setText("" + timerCounter + "秒后进入主页面");
            } else if(timeoutCallback != null) {
                timeoutCallback.run();
            }
        }
    } ;

    public void onLoading(String url) {
        if(this.timer != null) {
            this.timer.cancel();
            this.timer.purge();
        }
        //
        this.url = url;
        this.overlayLayer.setVisibility(View.VISIBLE);
        this.timer = new Timer();
        this.timerCounter = this.maxCounter;
        this.tv.setText("" + timerCounter + "秒后进入主页面");
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(timerProc);
            }
        }, 1000, 1000);
    }

    //
    public void onLoaded(String url) {
        if(this.timer == null || this.url == null || !this.url.equalsIgnoreCase(url) ) {
            return;
        }
        this.overlayLayer.setVisibility(View.GONE);
        this.timer.cancel();
        this.timer.purge();
        this.timer = null;
    }
}
