package com.dy.vam.ui.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.SummaryDepartmentData;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.fragment.depart.CEODepartFragment;
import com.dy.vam.ui.fragment.depart.OtherDepartFragment;
import com.dy.vam.ui.fragment.depart.StaffDepartFragment;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/2/5.
 * 部门  此处处理不同角色显示不同界面
 */

public class DepartFragment extends BaseFragment {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_depart;
    }

    @Override
    protected void initViews() {

    }

    /**
     * 加载部门首页
     */
    private void loadDepartInfo() {
        createRequest(BaseUrl.getInstence().getSummaryGetDepartmentDataUrl()).getSummaryDepartmentData(PreferenceUtils.getInstance().getUserToken(),0,PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<SummaryDepartmentData>() {
            @Override
            public void onResponse(Call<SummaryDepartmentData> call, Response<SummaryDepartmentData> response) {
                if (response.body()!=null){
                    if (response.body().getCode() == Constant.SUCCESS) {
                        if (response.body().getData() != null&&response.body().getData().getDepartment()!=null&&response.body().getData().getDepartment().size()>0) {
                            manager = getChildFragmentManager();
                            transaction = manager.beginTransaction();
                            initFragmentByRoleType(response.body().getData().getRole(),response.body());
                        } else {
                            Toasty.error(getActivity(), "获取部门信息为空！").show();
                        }
                    } else {
                        Toasty.error(getActivity(), response.body().getMsg()).show();
                    }
                }else{
                    Toasty.error(getActivity(),getString(R.string.net_error) + ":获取部门信息失败").show();
                }

            }

            @Override
            public void onFailure(Call<SummaryDepartmentData> call, Throwable t) {
                Toasty.error(getActivity(),getString(R.string.net_error) + ":获取部门信息失败").show();
                Log.e("获取部门信息:", t.toString());
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Intent intent = new Intent(Constant.ACTION);
            getActivity().sendBroadcast(intent); // 发送广播
        }
    }

    private void initFragmentByRoleType(int i, SummaryDepartmentData body) {//这里根据不同角色展示不同Fragment
        switch (i) {
            case 0:
                transaction.replace(R.id.layout_depart, new StaffDepartFragment(body));
                break;
            case 1:
                transaction.replace(R.id.layout_depart, new OtherDepartFragment(body));
                break;
            case 2:
                transaction.replace(R.id.layout_depart, new CEODepartFragment(body));
                break;
        }

        transaction.commitAllowingStateLoss ();
    }

    @Override
    protected void initDatas() {
        loadDepartInfo();
    }


}
