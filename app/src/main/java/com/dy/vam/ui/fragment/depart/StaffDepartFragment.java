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
import com.dy.vam.ui.fragment.StaffIntegralFragment;
import com.dy.vam.ui.fragment.StaffProportionFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2018/1/9.
 * 员工部门首页
 */

public class StaffDepartFragment extends BaseFragment {


    @BindView(R.id.layout_tab)
    RelativeLayout layoutTab;
    @BindView(R.id.tab_depart)
    TextView tabDepart;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    SummaryDepartmentData body;

    public StaffDepartFragment(SummaryDepartmentData body) {
        this.body = body;
    }

    public StaffDepartFragment() {
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_staff_depart;
    }

    @Override
    protected void initViews() {
        manager = this.getChildFragmentManager();
        transaction = manager.beginTransaction();
        initFragmentByType(body.getData().getDepartment().get(0).getAllot());
        tabDepart.setText(body.getData().getDepartment().get(0).getName());
    }

    private void initFragmentByType(int i) {
        switch (i) {
            case 0:
                transaction.replace(R.id.layout_content, new StaffProportionFragment(body));
                break;
            case 1:
                transaction.replace(R.id.layout_content, new StaffIntegralFragment(body));
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
                WebViewActivity.actionStart(getActivity(),"名词解释",body.getData().getHelp_url());
                break;
            case R.id.image_message:  MessageActivity.actionStart(getActivity(), "消息");
                break;
        }
    }
}
