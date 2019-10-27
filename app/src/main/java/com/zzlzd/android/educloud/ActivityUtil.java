package com.zzlzd.android.educloud;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtil {
    public static void goH5Pay(Activity activity,boolean isClose,boolean isFirstUse){
            Intent intent = new Intent(activity, H5PayDemoActivity.class);

		/*
		 * URL 是要测试的网站，在 Demo App 中会使用 H5PayDemoActivity 内的 WebView 打开。
		 *
		 * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
		 * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
		 * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
		 * 进行测试。
		 *
		 * H5PayDemoActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
		 * 可以参考它实现自定义的 URL 拦截逻辑。
		 */
		String url = UrlGloble.HOMEURL;
		intent.putExtra("url", url);
		intent.putExtra("isFirstUse", isFirstUse);
		activity.startActivity(intent);
	//
        if (isClose){
			activity.finish();
		}

    }
}
