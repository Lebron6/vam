package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.widget.TitleBarManger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2018/3/1.
 * 关于我们
 */

public class AboutUsActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.tv_version)
    TextView tvVersion;

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, AboutUsActivity.class);
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
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initViews() {
        tvVersion.setText("V:" + Utils.getLocalVersionName(this));
    }

    @Override
    protected void initDatas() {

    }


    @OnClick(R.id.layout_agreement)
    public void onViewClicked() {
        WebViewActivity.actionStart(AboutUsActivity.this, "", BaseUrl.getInstence().getSoftwareProtocolUrl());
    }

}
