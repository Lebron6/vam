package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.ControllableListChildBean;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.CCDetailsForMonthAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by James on 2018/1/30.
 * 可控制成本详情 下方Fragment
 */

public class CCDetailsForMonthFragment extends BaseFragment {


    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_depart_number)
    TextView tvDepartNumber;
    @BindView(R.id.rv_person_list)
    RecyclerView rvPersonList;

    private String money;
    private String departmentName;
    private List<ControllableListChildBean> controllableListChildBeen;

    public CCDetailsForMonthFragment(String s, List<ControllableListChildBean> controllableListChildBeen, String departmentName) {
        this.money = s;
        this.departmentName = departmentName;
        this.controllableListChildBeen = controllableListChildBeen;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_cc_details;
    }

    @Override
    protected void initViews() {
        tvDepartNumber.setText(departmentName + "(" + controllableListChildBeen.size() + ")");
        tvMoney.setText(Utils.stringToKeep2Point(String.valueOf(money)));
        CCDetailsForMonthAdapter adapter = new CCDetailsForMonthAdapter(getActivity());
        adapter.setDatas(controllableListChildBeen);
        RecyclerViewHelper.initRecyclerViewV(getActivity(), rvPersonList, false, adapter);
    }

    @Override
    protected void initDatas() {


    }

}
