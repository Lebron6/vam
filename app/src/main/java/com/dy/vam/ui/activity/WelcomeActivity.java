package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.dy.vam.R;
import com.gyf.barlibrary.BarHide;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/7/22.
 */
public class WelcomeActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar) .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                MainActivity.actionStart(WelcomeActivity.this, "Main");
                finish();
            }
        };
        timer.schedule(task, 1000 * 3);
    }

    @Override
    protected void initDatas() {

    }
}
