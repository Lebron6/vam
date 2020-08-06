package com.dy.vam.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dy.vam.R;
import com.dy.vam.config.Constant;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.widget.TabNavitationLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by James on 2018/1/16.
 * 审批状态 此界面展示审批Activity下的 全部 审批中 审批完成
 */

public class ExamineApproveStatusFragment extends BaseFragment {

    @BindView(R.id.tab_examine_approve_status)
    TabNavitationLayout tabExamineApproveStatus;
    @BindView(R.id.vp_examine_approve_status)
    ViewPager vpExamineApproveStatus;
    private int type;       //用来标记是费用审批、还是任务审批

    public ExamineApproveStatusFragment(int type) {
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_examine_approve_status;
    }

    @Override
    protected void initViews() {
        String[] titles2 = new String[]{"审批中","全部"};
        List<Fragment> fragments2 = new ArrayList<>();
        fragments2.add(new ExamineApproveListFragment(type, Constant.EXAMINE_APPROVE_ING));
        fragments2.add(new ExamineApproveListFragment(type,Constant.EXAMINE_APPROVE_ALL));
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getChildFragmentManager(), fragments2);
        vpExamineApproveStatus.setAdapter(viewPagerAdapter2);
        tabExamineApproveStatus.setViewPager(getActivity(), titles2, vpExamineApproveStatus, R.color.white, R.color.white, R.color.white, R.color.colorTextGray, R.color.colorTheme, Utils.px2dip(getActivity(), getResources().getDimension(R.dimen.x32)), 0, 1f, true);
    }

    @Override
    protected void initDatas() {

    }
}
