package com.dy.vam.ui.fragment.depart;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.ui.activity.MessageActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.fragment.BaseFragment;
import com.dy.vam.ui.fragment.OtherIntegralFragment;
import com.dy.vam.ui.fragment.OtherProportionFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2018/1/9.
 * 除员工、经理外 其余部门：财务、营销等 部门首页
 */

public class OtherDepartFragment extends BaseFragment {


    @BindView(R.id.layout_tab)
    RelativeLayout layoutTab;
    @BindView(R.id.tab_depart)
    TextView tabDepart;
    Unbinder unbinder;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private SummaryDepartmentData summaryDepartmentData;

    public OtherDepartFragment(SummaryDepartmentData summaryDepartmentData) {
        this.summaryDepartmentData = summaryDepartmentData;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_other_depart;
    }

    @Override
    protected void initViews() {
        manager = this.getChildFragmentManager();
        transaction = manager.beginTransaction();
        initFragmentByType(summaryDepartmentData.getData().getDepartment().get(0).getAllot());
        tabDepart.setText(summaryDepartmentData.getData().getDepartment().get(0).getName());
    }

    private void initFragmentByType(int i) {
        switch (i) {
            case 0:
                transaction.replace(R.id.layout_content, new OtherProportionFragment(summaryDepartmentData));
                break;
            case 1:
                transaction.replace(R.id.layout_content, new OtherIntegralFragment(summaryDepartmentData));
                break;
        }
        transaction.commit();
    }

    @Override
    protected void initDatas() {
    }


    @OnClick({R.id.image_question, R.id.image_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_question:
                WebViewActivity.actionStart(getActivity(),"名词解释",summaryDepartmentData.getData().getHelp_url());
                break;
            case R.id.image_message:  MessageActivity.actionStart(getActivity(), "消息");
                break;
        }
    }

}
