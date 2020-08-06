package com.dy.vam.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.dy.vam.R;
import com.dy.vam.entity.TestIntance;

import cn.jpush.android.api.JPushInterface;
import es.dmoral.toasty.Toasty;

/**
 * Created by James on 2018/1/9.
 */

public class VAMApplication extends Application{
    private static Context application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initToasty();
        initJPush();

    }

    public static Context getApplication(){
        return application;
    }
    private void initToasty() {
        Toasty.Config.getInstance()
//                .setErrorColor(getResources().getColor(R.color.colorTrRed)) // optional
                .setErrorColor(getResources().getColor(R.color.colorTextTheme)) // optional
                .setInfoColor(getResources().getColor(R.color.colorTextTheme)) // optional
                .setSuccessColor(getResources().getColor(R.color.colorTrGreen)) // optional
                .setWarningColor(getResources().getColor(R.color.colorTrYellow)) // optional
                .setTextColor(getResources().getColor(R.color.colorWhite)) // optional
                .tintIcon(true) // optional (apply textColor also to the icon)
                .setToastTypeface(Typeface.MONOSPACE)
                .setTextSize(12) // optional
                .apply(); // required
    }

    private void initJPush() {
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    }
}
