package com.zzlzd.android.educloud.Interface;

public interface PublicCallBack<T> {
    /**
     * 返回得到的字符串
     *
     * @param t
     * @return
     */
    void onSuccess(int reqCode, T t);

    /**
     * 返回异常
     *
     * @param msg
     * @return
     */
    void onFail(int reqCode, String msg);
}
