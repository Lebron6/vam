package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.dy.vam.R;
import com.dy.vam.entity.OperaList;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.OperaChildAdapter;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by James on 2018/5/8.
 */

public class OperaChildFragment extends BaseFragment {

    @BindView(R.id.recycle_opera_child)
    RecyclerView recycleOperaChild;
    Unbinder unbinder;
    private int type;
    private OperaList operaList;

    public OperaChildFragment(int type, OperaList operaList) {
        this.type = type;
        this.operaList = operaList;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera_child;
    }

    @Override
    protected void initViews() {

        OperaChildAdapter operaChildAdapter=new OperaChildAdapter(getActivity());
        switch (type) {
            case 0: //通用
                operaChildAdapter.setDatas(operaList,0,operaList.getData().getOperation().size());
                RecyclerViewHelper.initRecyclerViewV(getActivity(), recycleOperaChild, false, operaChildAdapter);
                break;
            case 1:  //管理
                operaChildAdapter.setDatas(operaList,1,operaList.getData().getManage().size());
                RecyclerViewHelper.initRecyclerViewV(getActivity(), recycleOperaChild, false, operaChildAdapter);
                break;
            case 2:  //查看
                operaChildAdapter.setDatas(operaList,2,operaList.getData().getView().size());
                RecyclerViewHelper.initRecyclerViewV(getActivity(), recycleOperaChild, false, operaChildAdapter);
                break;
        }
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
