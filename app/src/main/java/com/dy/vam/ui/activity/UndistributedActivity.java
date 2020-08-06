package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.UnDistributed;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.UndistributedAdapter;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/3/19.
 * 价值/提成未分配人员
 */

public class UndistributedActivity extends BaseActivity {

    private static final String TYPE = "type";
    private static final String TITLE = "title";
    @BindView(R.id.rv_undistributed)
    RecyclerView rvUndistributed;
    private UndistributedAdapter adapter;
    private Call<UnDistributed> undistributed;
    private int num=0;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title, int type) {
        Intent intent = new Intent(context, UndistributedActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(TITLE));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_undistributed;
    }

    @Override
    protected void initViews() {
        adapter=new UndistributedAdapter(this);
    }

    @Override
    protected void initDatas() {
        switch (getIntent().getIntExtra(TYPE,1)){
            case 1:     //提成
                undistributed = createRequest(BaseUrl.getInstence().getCommissionUndistrubuteUrl()).getUndistributed(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear());
                break;
            case 2:     //价值
                undistributed = createRequest(BaseUrl.getInstence().getValueUndistrubuteUrl()).getUndistributed(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear());
                break;
        }
        undistributed.enqueue(new Callback<UnDistributed>() {
            @Override
            public void onResponse(Call<UnDistributed> call, Response<UnDistributed> response) {
                if (response.body()!=null&&response.body().getCode()==0){
                    switch (response.body().getCode()) {
                        case 0:
                            //初始化部门成员列表数据
                            if (response.body().getData()!=null){
                                if (response.body().getData().get_$1().size()>0){
                                    num = num+1;
                                }
                                if (response.body().getData().get_$2().size()>0){
                                    num = num+1;
                                }
                                if (response.body().getData().get_$3().size()>0){
                                    num = num+1;
                                }
                                if (response.body().getData().get_$4().size()>0){
                                    num = num+1;
                                }
                                adapter.setDatas(response.body(), num);
                                RecyclerViewHelper.initRecyclerViewV(UndistributedActivity.this, rvUndistributed, false, adapter);
                            }else{
                                Toasty.error(UndistributedActivity.this, "暂无未分配人员").show();
                            }
                            break;
                        default:
                            Toasty.error(UndistributedActivity.this, response.body().getMsg()).show();
                            break;
                    }
                }else{
                    Toasty.error(UndistributedActivity.this, getString(R.string.net_error)+":获取未分配列表异常").show();
                }

            }

            @Override
            public void onFailure(Call<UnDistributed> call, Throwable t) {
                Toasty.error(UndistributedActivity.this, getString(R.string.net_error)+":获取未分配列表异常").show();
            }
        });

    }
}
