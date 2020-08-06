package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.ui.fragment.AllocatingTableCrossFragment;
import com.dy.vam.ui.fragment.ValueDistributionTableCrossFragment;
import com.dy.vam.ui.widget.CustomViewPager;
import com.dy.vam.ui.widget.NavitationScrollLayout;
import com.dy.vam.ui.widget.TimeSelectWindow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2018/1/22.
 * 价值分配录入   横屏
 */

public class ValueDistributionInputCrossActivity extends BaseActivity {

    private static final String TYPE = "type";
    @BindView(R.id.layout_scroll)
    NavitationScrollLayout tabValueDisinput;
    @BindView(R.id.id_stickynavlayout_viewpager)
    CustomViewPager vpValueDistributionInput;
    @BindView(R.id.layout_show_quarter_window)
    RelativeLayout layoutShowQuarterWindow;
    @BindView(R.id.tv_quarter_banner)
    TextView tvQuarterBanner;
    @BindView(R.id.tv_year)
    TextView tvYear;
    private ArrayAdapter seasonsAdapter;
    private List<String> seasons;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar).statusBarColor(R.color.colorWhite)
                .init();
    }

    public static void actionStart(Context context, String title, String type) {
        Intent intent = new Intent(context, ValueDistributionInputCrossActivity.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_value_distribution_input_cross;
    }

    @Override
    protected void initViews() {
        List<String> titles = new ArrayList<>(); 
        titles.add(new String("价值分配表"));
        titles.add(new String("提成分配表"));
        List<Fragment> fragments2 = new ArrayList<>();
        fragments2.add(new ValueDistributionTableCrossFragment(getIntent().getStringExtra(TYPE)));
        fragments2.add(new AllocatingTableCrossFragment(getIntent().getStringExtra(TYPE)));
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager(), fragments2);
        vpValueDistributionInput.setAdapter(viewPagerAdapter2);
        tabValueDisinput.setViewPager(this, titles, vpValueDistributionInput, R.color.colorTextGray, R.color.colorTextTheme, Utils.px2dip(this, getResources().getDimension(R.dimen.x28)), Utils.px2dip(this, getResources().getDimension(R.dimen.x28)), 12, true, R.color.colorWhite, 0f, 15f, 15f, 100);
        tabValueDisinput.setBgLine(this, 1, R.color.colorAccent);
        tabValueDisinput.setNavLine(this, 3, R.color.colorWhite);
        tabValueDisinput.setOnNaPageChangeListener(onNaPageChangeListener);

        seasons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            seasons.add("第"+(i + 1)+"季度");
        }
        seasonsAdapter = new ArrayAdapter(ValueDistributionInputCrossActivity.this, R.layout.item_question, R.id.tv_popqusetion, seasons);
        tvYear.setText(PreferenceUtils.getInstance().getDefaultYear()+"");
        tvQuarterBanner.setText("第" + Utils.getSeason(new Date()) + "季度");
    }

    NavitationScrollLayout.OnNaPageChangeListener onNaPageChangeListener = new NavitationScrollLayout.OnNaPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    layoutShowQuarterWindow.setVisibility(View.GONE);
                    break;
                case 1:
                    layoutShowQuarterWindow.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void initDatas() {

    }


    @OnClick(R.id.layout_back)
    public void onViewClicked() {

    }


    @OnClick({R.id.layout_show_quarter_window, R.id.layout_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_show_quarter_window:
                TimeSelectWindow seasonSelectWindow = new TimeSelectWindow(ValueDistributionInputCrossActivity.this);
                seasonSelectWindow.showView(layoutShowQuarterWindow, seasonsAdapter, seasonSelectedListener);
                break;
            case R.id.layout_back:
                finish();
                break;
        }
    }

    OnTimeSelectedListener seasonSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            AllocatingTableCrossFragment.sendBroadCast(ValueDistributionInputCrossActivity.this, postion+1);
            tvQuarterBanner.setText(seasons.get(postion) );
        }
    };

}
