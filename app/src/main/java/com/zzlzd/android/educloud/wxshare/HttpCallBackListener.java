package com.zzlzd.android.educloud.wxshare;

import android.graphics.Bitmap;

/**
 * @Auther: Administrator
 * @Date: 2019/1/25 09:53
 * @Description:
 */
public interface HttpCallBackListener {
    void onFinish(Bitmap bitmap);
    void onError(Exception e);
}
