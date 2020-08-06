package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.dy.vam.R;
import com.dy.vam.ui.fragment.AllocatingTableCrossFragment;
import com.dy.vam.ui.widget.TitleBarManger;

import static com.dy.vam.config.Constant.OperaConstant.DISTRIBUTION;

/**
 * Created by James on 2018/1/30.
 * 提成分配表 、财务录入Adapter跳转
 */

public class AllocatingTableActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, AllocatingTableActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
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
        return R.layout.activity_allocating_table;
    }

    @Override
    protected void initViews() {
getSupportFragmentManager().beginTransaction().replace(R.id.frame_contant,new AllocatingTableCrossFragment( DISTRIBUTION)).commit();
    }

    @Override
    protected void initDatas() {

    }
}
