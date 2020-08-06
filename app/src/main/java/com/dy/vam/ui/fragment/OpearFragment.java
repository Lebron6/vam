package com.dy.vam.ui.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.dy.vam.R;
import com.dy.vam.ui.fragment.opear.AdminDirectorOpearFragment;
import com.dy.vam.ui.fragment.opear.CEOOpearFragment;
import com.dy.vam.ui.fragment.opear.FinancialControllerOpearFragment;
import com.dy.vam.ui.fragment.opear.MarketingDirectorOpearFragment;
import com.dy.vam.ui.fragment.opear.StaffOpearFragment;

/**
 * Created by James on 2018/1/9.
 * 操作  此处处理不同角色显示不同界面
 */

public class OpearFragment extends BaseFragment {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opear;
    }

    @Override
    protected void initViews() {
        manager = this.getChildFragmentManager();
        transaction = manager.beginTransaction();
        initFragmentByRoleType(0);

    }

    private void initFragmentByRoleType(int i) {//这里根据不同角色展示不同Fragment
        switch (i){
            case 0:
                transaction.replace(R.id.layout_opera, new FinancialControllerOpearFragment());
                break;
            case 1:
                transaction.replace(R.id.layout_opera, new AdminDirectorOpearFragment());
                break;
            case 2:
                transaction.replace(R.id.layout_opera, new StaffOpearFragment());
                break;
            case 3:
                transaction.replace(R.id.layout_opera, new MarketingDirectorOpearFragment());
                break;
            case 4:
                transaction.replace(R.id.layout_opera, new CEOOpearFragment());
                break;
        }

        transaction.commit();
    }


    @Override
    protected void initDatas() {

    }


}
