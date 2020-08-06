package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import com.dy.vam.R;
import com.dy.vam.ui.widget.TitleBarManger;

/**
 * Created by James on 2018/1/30.
 * 审批提交成功界面  决定是否用H5还是原生
 */

public class CommitSuccessActivity extends BaseActivity{

    public static final String DETAILS_VALUES = "title";

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, CompanyTRevenueActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_commit_success;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
