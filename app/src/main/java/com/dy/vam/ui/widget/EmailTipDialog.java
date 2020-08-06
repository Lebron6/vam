package com.dy.vam.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dy.vam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Rain
 * created:2019/5/31 9:30
 * desc:类作用描述
 */
public class EmailTipDialog extends Dialog {


    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.al_close)
    RelativeLayout alClose;
    private View view;
    private Context context;
    private String emailStr;
    private View.OnClickListener onClickListener;
    public EmailTipDialog(Context context) {
        super(context);
    }

    public EmailTipDialog(Context context,String emailStr ,int themeResId,View.OnClickListener onClickListener) {
        super(context, themeResId);
        this.context = context;
        this.emailStr = emailStr;
        this.onClickListener = onClickListener;
        view = LinearLayout.inflate(context, R.layout.dialog_email_hint, null);
    }

    protected EmailTipDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public String getEmailStr() {

        return editEmail.getText().toString();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this, view);
        setContentView(view);
        btnSure.setOnClickListener(onClickListener);
        if (!TextUtils.isEmpty(emailStr)){
            editEmail.setText(emailStr);
        }
    }


    @OnClick({R.id.btn_sure, R.id.al_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:

                break;
            case R.id.al_close:
                dismiss();
                break;
        }
    }

}
