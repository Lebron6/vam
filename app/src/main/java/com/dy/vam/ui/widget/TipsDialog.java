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
public class TipsDialog extends Dialog {


    public TipsDialog(Context context) {
        super(context);
    }

    public TipsDialog(Context context, String msg, DialogClickListener dialogClickListener) {
        super(context, R.style.MyDialog);
        this.dialogClickListener = dialogClickListener;
        this.msg = msg;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_web_view);
        initView();
    }

    private void initView() {
        TextView tv_msg = findViewById(R.id.tv_msg);
        TextView tvNo = findViewById(R.id.tv_no);
        TextView tvYes = findViewById(R.id.tv_yes);
        tv_msg.setText(msg + "");
        tvNo.setOnClickListener(onClickListener);
        tvYes.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_no:
                    dialogClickListener.no();
                    dismiss();
                    break;
                case R.id.tv_yes:
                    dialogClickListener.yes();
                    dismiss();
                    break;
            }
        }
    };

    public DialogClickListener dialogClickListener;
    public String msg;

    public interface DialogClickListener {
        void yes();

        void no();
    }
}
