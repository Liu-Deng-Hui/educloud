package com.zzlzd.android.educloud.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zzlzd.android.educloud.Constants;
import com.zzlzd.android.educloud.H5PayDemoActivity;
import com.zzlzd.android.educloud.R;
import com.zzlzd.android.educloud.UrlGloble;
import com.zzlzd.android.view.ResultDialog;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
        Log.e("",req.toString());
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
		    if(resp.errCode==0){
		        String url= UrlGloble.paySucUrl+ H5PayDemoActivity.oderid;
                Intent intent = new Intent(WXPayEntryActivity.this, H5PayDemoActivity.class);
                //intent.putExtra("url",url);
                //intent.putExtra("fromWX",true);
                H5PayDemoActivity.FROMWX = true;
                startActivity(intent);
                finish();
            }else if(resp.errCode==-2) {//用户取消
                this.finish();
            }else{//失败
                ResultDialog dialog=new ResultDialog(WXPayEntryActivity.this);
                dialog.setContent("支付失败");
                dialog.setCancelable(false);
                dialog.show();
            }

		}

	}
}