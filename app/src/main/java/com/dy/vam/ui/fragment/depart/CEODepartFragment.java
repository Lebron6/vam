package com.dy.vam.ui.fragment.depart;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.activity.MessageActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.ui.fragment.BaseFragment;
import com.dy.vam.ui.fragment.CEOIntegralFragment;
import com.dy.vam.ui.fragment.CEOProportionFragment;
import com.dy.vam.ui.widget.NavitationScrollLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2018/1/9.
 * 总经理查看部门
 */

public class CEODepartFragment extends BaseFragment {


    @BindView(R.id.tab_depart)
    NavitationScrollLayout tabDepart;
    @BindView(R.id.layout_tab)
    RelativeLayout layoutTab;
    @BindView(R.id.vp_department_value)
    ViewPager vpDepartmentValue;
    SummaryDepartmentData body;

    public CEODepartFragment() {

    }

    public CEODepartFragment(SummaryDepartmentData body) {
        this.body = body;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_ceo_depart;
    }

    @Override
    protected void initViews() {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments2 = new ArrayList<>();
        for (int i = 0; i < body.getData().getDepartment().size(); i++) {
            titles.add(body.getData().getDepartment().get(i).getName());
            if (body.getData().getDepartment().get(i).getAllot() == Constant.System.PROPORTION) {
                fragments2.add(new CEOProportionFragment(body, i));
            } else {
                fragments2.add(new CEOIntegralFragment(body, i));
            }
        }
        ViewPagerAdapter viewPagerAdapter2 = new ViewPagerAdapter(getChildFragmentManager(), fragments2);
        vpDepartmentValue.setAdapter(viewPagerAdapter2);
        tabDepart.setViewPager(getActivity(), titles, vpDepartmentValue, R.color.colorWhite, R.color.colorTextTheme, Utils.px2dip(getActivity(), getResources().getDimension(R.dimen.x28)), Utils.px2dip(getActivity(), getResources().getDimension(R.dimen.x28)), 12, true, R.color.colorWhite, 0f, 15f, 15f, Utils.px2dip(getActivity(), getResources().getDimension(R.dimen.x160)));
        tabDepart.setBgLine(getActivity(), 1, R.color.colorAccent);
        tabDepart.setNavLine(getActivity(), 3, R.color.colorTheme);
    }

    @Override
    protected void initDatas() {
    }

    @OnClick({R.id.image_question, R.id.image_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_question:
                WebViewActivity.actionStart(getActivity(), "名词解释", body.getData().getHelp_url());
                break;
            case R.id.image_message:
                MessageActivity.actionStart(getActivity(), "消息");
                break;
        }
    }


}
