package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.dy.vam.R;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.fragment.BusinessPromotionFragment;
import com.dy.vam.ui.widget.TabNavitationLayout;
import com.dy.vam.ui.widget.TitleBarManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2018/1/16.
 * 业务提成录入
 */

public class BusinessPromotionActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "id";
    @BindView(R.id.tab_business_promotion)
    TabNavitationLayout tabBusinessPromotion;
    @BindView(R.id.vp_business_promotion)
    ViewPager vpBusinessPromotion;


    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, BusinessPromotionActivity.class);
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
        return R.layout.activity_business_promotion;
    }

    @Override
    protected void initViews() {
      String[]  titles2 = new String[]{"新增业务", "全部"};
       List<Fragment> fragments2 = new ArrayList<>();
        fragments2.add(new BusinessPromotionFragment(0));
        fragments2.add(new BusinessPromotionFragment(1));
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager(), fragments2);
        vpBusinessPromotion.setAdapter(viewPagerAdapter2);
        tabBusinessPromotion.setViewPager(this, titles2, vpBusinessPromotion, R.color.white,R.color.white, R.color.white, R.color.colorTextGray,R.color.colorTheme, Utils.px2dip(this,getResources().getDimension(R.dimen.x32)) , 0, 1f, true);
    }

    @Override
    protected void initDatas() {

    }

}
