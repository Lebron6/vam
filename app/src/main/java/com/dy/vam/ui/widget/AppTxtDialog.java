package com.dy.vam.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.tools.PreferenceUtils;

/**
 * Created by Administrator on 2016/7/5.
 */
public class AppTxtDialog extends Dialog {


    private CheckBox cbKnow;
    private Button btnKnow;

    public AppTxtDialog(Context context) {
        super(context);
    }

    public AppTxtDialog(Context context, int theme) {
        super(context, theme);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_app_txt);
        initView();
    }

    private void initView() {
        cbKnow = findViewById(R.id.cb_know);
        btnKnow = findViewById(R.id.btn_know);
        btnKnow.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_know:
                    if (cbKnow.isChecked() == true) {
                        PreferenceUtils.getInstance().setAppTxtKnow(true);
                        dismiss();
                    } else {
                        dismiss();
                    }
                    break;
            }
        }
    };

}
