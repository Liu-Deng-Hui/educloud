package com.zzlzd.android.educloud;

import android.content.Context;
import android.util.Log;

import com.zzlzd.android.educloud.Interface.PublicCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    public void getServer(final Context mContext, String type, String orderId, final PublicCallBack callBack) {
        final String url = UrlGloble.url + "?payType=" + type + "&order_id=" + orderId;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("", "onFailure: ");
                callBack.onFail(0, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(0, response.body().string());
            }
        });
    }

}
