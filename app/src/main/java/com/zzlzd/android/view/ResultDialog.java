package com.zzlzd.android.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zzlzd.android.educloud.R;

public class ResultDialog extends Dialog {

    private Context context;
    private TextView content;
    private TextView btn;
//    private ClickLister leftClick,rightClick;

    public ResultDialog(@NonNull Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
        initView();
    }


    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_dialog, null);
        setContentView(view);

        content = (TextView) view.findViewById(R.id.dialog_content);
        btn = (TextView) view.findViewById(R.id.but);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                ((Activity)context).finish();
            }
        });
    }

    public void setContent(String contentStr){
        content.setText(contentStr);
    }


}
