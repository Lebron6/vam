package com.dy.vam.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.vam.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 2016/7/5.
 */
public class PromptDialog extends Dialog {


    private EditText et_msg;
    private Context context;

    public PromptDialog(Context context) {
        super(context);
    }

    public PromptDialog(Context context, DialogClickListener dialogClickListener) {
        super(context, R.style.MyDialog);
        this.dialogClickListener = dialogClickListener;
        this.context = context;

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_prompt);
        initView();
    }

    private void initView() {
        et_msg = findViewById(R.id.et_msg);
        TextView tvNo = findViewById(R.id.tv_no);
        TextView tvYes = findViewById(R.id.tv_yes);
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
                    String msg=et_msg.getText().toString();
                    if (TextUtils.isEmpty(msg)){
                        Toasty.error(context,"请输入！").show();
                        return;
                    }
//                    if (Integer.valueOf(msg)>100){
//                        Toasty.error(context,"比例不得大于100%！").show();
//                        return;
//                    }
                    dialogClickListener.yes(et_msg.getText().toString());
                    dismiss();
                    break;
            }
        }
    };

    public DialogClickListener dialogClickListener;

    public interface DialogClickListener {
        void yes(String msg);

        void no();
    }
}
