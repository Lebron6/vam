package com.dy.vam.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.application.VAMApplication;

import es.dmoral.toasty.Toasty;


/**
 * Created by 齐天大圣 on 2016/8/1.
 */
public class VersionUpdateDialog extends Dialog {
    private View.OnClickListener onNegativeListener;
    private View.OnClickListener onPositiveListener;
    private Button positiveBtn;
    private Button negativeBtn;
    private TextView msg;
    private String positiveText = "确定";
    private String negativeText = "取消";
    private SpannableStringBuilder message;
    private TextView title;
    private String titles = "温馨提示";
    private View inflate;

    public VersionUpdateDialog(Context context) {
        super(context);
    }

    public VersionUpdateDialog(Context context, int theme) {
        super(context, theme);
        inflate = LinearLayout.inflate(context, R.layout.dialog_version_update, null);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inflate);
    }


    public VersionUpdateDialog setPositiveButton(String text,
                                           View.OnClickListener onClickListener) {
        positiveText = text;
        onPositiveListener = onClickListener;
        return this;
    }

    public VersionUpdateDialog setNegativeButton(String text,
                                           View.OnClickListener onClickListener) {
        negativeText = text;
        onNegativeListener = onClickListener;
        return this;
    }

    public View getView(){
        return inflate;
    }

    public VersionUpdateDialog setMessage(String message1) {
        titles = message1;
        return this;
    }
    public VersionUpdateDialog setTitle(String message1) {
        message = new SpannableStringBuilder(message1);
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
