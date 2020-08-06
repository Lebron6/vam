package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.dy.vam.R;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.ui.fragment.AllocatingTableCrossFragment;
import com.dy.vam.ui.fragment.AllocatingTableFragment;
import com.dy.vam.ui.fragment.ValueDistributionTableCrossFragment;
import com.dy.vam.ui.fragment.ValueDistributionTableFragment;
import com.dy.vam.ui.widget.CustomViewPager;
import com.dy.vam.ui.widget.NavitationScrollLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2018/1/22.
 * 价值分配录入   竖屏
 */

public class ValueDistributionInputVerticalActivity extends BaseActivity {

private static final String TYPE="type";
    @BindView(R.id.tab_value_disinput)
    NavitationScrollLayout tabValueDisinput;
    @BindView(R.id.vp_value_distribution_input)
    CustomViewPager vpValueDistributionInput;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true,0.2f).statusBarView(R.id.view_status_bar).statusBarColor(R.color.colorWhite)
                .init();
    }
    public static void actionStart(Context context, String title, String type) {
        Intent intent = new Intent(context, ValueDistributionInputVerticalActivity.class);
        intent.putExtra(TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_value_distribution_input_vertical;
    }

    @Override
    protected void initViews() {
        List<String> titles=new ArrayList<>();
        titles.add(new String("价值分配表"));
        titles.add(new String("提成分配表"));
        List<Fragment> fragments2 = new ArrayList<>();
        fragments2.add(new ValueDistributionTableFragment(getIntent().getStringExtra(TYPE)));
        fragments2.add(new AllocatingTableFragment(getIntent().getStringExtra(TYPE)));

        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager(), fragments2);
        vpValueDistributionInput.setAdapter(viewPagerAdapter2);
        tabValueDisinput.setViewPager(this, titles, vpValueDistributionInput, R.color.colorTextGray, R.color.colorTextTheme, Utils.px2dip(this, getResources().getDimension(R.dimen.x28)), Utils.px2dip(this, getResources().getDimension(R.dimen.x36)), 12, true, R.color.colorWhite, 0f, 15f, 15f, 100);
        tabValueDisinput.setBgLine(this, 1, R.color.colorAccent);
        tabValueDisinput.setNavLine(this, 3, R.color.colorWhite);
    }

    @Override
    protected void initDatas() {

    }


    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }


}
