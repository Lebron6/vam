package com.dy.vam.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dy.vam.R;

/**
 * Created by Administrator on 2016/7/5.
 */
public class CustomDialog extends Dialog  {


    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme,DialogClickListener dialogClickListener) {
        super(context, theme);
        this.dialogClickListener=dialogClickListener;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_income_type);
        initView();
    }

    private void initView() {
        TextView tvSure=findViewById(R.id.tv_sure);
        TextView tvCancel=findViewById(R.id.tv_cancel);
        tvSure.setOnClickListener(onClickListener );
        tvCancel.setOnClickListener(onClickListener );
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_sure:
                    dialogClickListener.sure();
                    break;
                case R.id.tv_cancel:
                   dismiss();
                    break;
            }
        }
    };


    public DialogClickListener dialogClickListener;

    public interface DialogClickListener{
       void sure();
    }
}
