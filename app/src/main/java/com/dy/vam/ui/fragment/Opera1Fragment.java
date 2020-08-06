package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.OperaList;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.OperaAdapter;
import com.dy.vam.ui.widget.LoadDialog;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/2/6.
 */

public class Opera1Fragment extends BaseFragment {
    @BindView(R.id.rv_opera)
    RecyclerView rvOpera;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera_one;
    }

    @Override
    protected void initViews() {
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getOperationListUrl()).getOperationList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<OperaList>() {
            @Override
            public void onResponse(Call<OperaList> call, Response<OperaList> response) {
                LoadDialog.dismiss(getActivity());
                if (response!=null&&response.body()!=null){
                    switch (response.body().getCode()){
                        case Constant.SUCCESS:
                            OperaAdapter adapter = new OperaAdapter(getActivity());
                            adapter.setDatas(response.body());
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvOpera, false, adapter);
                            break;
                        default:
                            Toasty.error(getActivity(),response.body().getMsg()).show();
                            break;
                    }
                }else{
                    Toasty.error(getActivity(),getString(R.string.net_error)+":获取操作标签失败！").show();
                }

            }

            @Override
            public void onFailure(Call<OperaList> call, Throwable t) {
                LoadDialog.dismiss(getActivity());
                Toasty.error(getActivity(),getString(R.string.net_error)+":获取操作标签失败！").show();
                Log.e("获取操作标签",t.toString());
            }
        });
    }

    @Override
    protected void initDatas() {

    }

}
