package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dy.vam.R;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.ui.fragment.ExamineApproveListFragment;
import com.dy.vam.ui.fragment.ExamineApproveStatusFragment;
import com.dy.vam.ui.widget.TabNavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_ALL;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_CC;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_CC;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_COST;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_TASK;

/**
 * Created by James on 2018/1/16.
 * 审批 此界面除员工外都复用
 */

public class ExamineApproveActivity extends BaseActivity {

    @BindView(R.id.tab_examine_approve)
    TabNavitationLayout tabExamineApprove;
    @BindView(R.id.vp_examine_approve)
    ViewPager vpExamineApprove;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true,0.2f).statusBarView(R.id.view_status_bar).statusBarColor(R.color.colorWhite)
                .init();
    }
    public static void actionStart(Context context,String title) {
        Intent intent = new Intent(context, ExamineApproveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_examine_approve;
    }

    @Override
    protected void initViews() {
        String[] titles2 = new String[]{"费用审批", "任务审批", "抄送我的"};      //抄送我的 第三个Fragment为抄送 直接显示列表
        List<Fragment> fragments2 = new ArrayList<>();
        fragments2.add(new ExamineApproveStatusFragment(EXAMINE_APPROVE_TYPE_COST));
        fragments2.add(new ExamineApproveStatusFragment(EXAMINE_APPROVE_TYPE_TASK));
        fragments2.add(new ExamineApproveListFragment(EXAMINE_APPROVE_TYPE_CC,EXAMINE_APPROVE_CC));
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getSupportFragmentManager(), fragments2);
        vpExamineApprove.setAdapter(viewPagerAdapter2);
        vpExamineApprove.setOffscreenPageLimit(2);
        tabExamineApprove.setViewPager(this, titles2, vpExamineApprove, R.color.colorWhite, R.color.colorWhite, R.color.colorWhite, R.color.colorTextGray, R.color.colorTextTheme, Utils.px2dip(this, getResources().getDimension(R.dimen.x32)), 0, 1f, true);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
