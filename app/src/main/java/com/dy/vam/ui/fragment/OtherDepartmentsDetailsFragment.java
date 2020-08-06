package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import com.dy.vam.R;
import com.dy.vam.entity.OtherDepartmentDetailsData;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.OtherDepartmentDetailsControlAdapter;
import com.dy.vam.ui.adapter.OtherDepartmentDetailsControlAdapter_;
import com.dy.vam.ui.adapter.OtherDepartmentDetailsDisposableAdapter;
import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * Created by James on 2018/1/25.
 * 其它部门详情底部Fragment
 */

public class OtherDepartmentsDetailsFragment extends BaseFragment {
    @BindView(R.id.rv_business_promotion)
    RecyclerView rvBusinessPromotion;
    private OtherDepartmentDetailsData otherDepartmentDetailsData;
    private int type;

    public OtherDepartmentsDetailsFragment(int type, OtherDepartmentDetailsData otherDepartmentDetailsData) {
        this.otherDepartmentDetailsData = otherDepartmentDetailsData;
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_business_promotion;
    }

    @Override
    protected void initViews() {
        switch (type) {
            case 0:
                if (otherDepartmentDetailsData.getData().getDisposable_list() != null) {
                    OtherDepartmentDetailsControlAdapter otherDepartmentDetailsDisposableAdapter = new OtherDepartmentDetailsControlAdapter(getActivity());
                    otherDepartmentDetailsDisposableAdapter.setDatas(otherDepartmentDetailsData);
                    RecyclerViewHelper.initRecyclerViewV(getActivity(), rvBusinessPromotion, false, otherDepartmentDetailsDisposableAdapter);
                } else {
                    Toasty.error(getActivity(), "获取部门可支配收入列表失败").show();
                }

                break;
            case 1:
                if (otherDepartmentDetailsData.getData().getTotal_control() != null) {
                    OtherDepartmentDetailsControlAdapter_ otherDepartmentDetailsControlAdapter = new OtherDepartmentDetailsControlAdapter_(getActivity());
                    otherDepartmentDetailsControlAdapter.setDatas(otherDepartmentDetailsData);
                    RecyclerViewHelper.initRecyclerViewV(getActivity(), rvBusinessPromotion, false, otherDepartmentDetailsControlAdapter);
                } else {
                    Toasty.error(getActivity(), "获取部门可控制收入列表失败").show();
                }
                break;
        }

    }

    @Override
    protected void initDatas() {

    }

}
