package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Iteration;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.ModifyTheRecordAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.SUCCESS;

/**
 * Created by James on 2018/3/20.
 * 修改记录
 */

public class ModifyTheRecordActivity extends BaseActivity {

    private static final String DETAILS_VALUES = "title";
    private static final String TASK_ID = "taskid";
    @BindView(R.id.rv_modify_the_record)
    RecyclerView rvModifyTheRecord;
    @BindView(R.id.layout_data_null)
    RelativeLayout layoutDataNull;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, String title, int taskId) {
        Intent intent = new Intent(context, ModifyTheRecordActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(TASK_ID, taskId);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_modify_the_record;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        loadDatas();
    }

    private void loadDatas() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getExamineGetIterationListUrl()).iteration(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<Iteration>() {
            @Override
            public void onResponse(Call<Iteration> call, Response<Iteration> response) {
                LoadDialog.dismiss(ModifyTheRecordActivity.this);
                if (response.body() != null) {
                    if (response.body().getCode() == SUCCESS) {
                        if (response.body().getData() != null && response.body().getData().size() > 0) {
                            layoutDataNull.setVisibility(View.GONE);
                            ModifyTheRecordAdapter adapter = new ModifyTheRecordAdapter(ModifyTheRecordActivity.this);
                            adapter.setDatas(response.body());
                            RecyclerViewHelper.initRecyclerViewV(ModifyTheRecordActivity.this, rvModifyTheRecord, true, adapter);
                        } else {
                            layoutDataNull.setVisibility(View.VISIBLE);
                        }
                    } else {
                        layoutDataNull.setVisibility(View.VISIBLE);
                        Toasty.error(ModifyTheRecordActivity.this, response.body().getMsg()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Iteration> call, Throwable t) {
                layoutDataNull.setVisibility(View.VISIBLE);
                LoadDialog.dismiss(ModifyTheRecordActivity.this);
                Toasty.error(ModifyTheRecordActivity.this, getString(R.string.net_error) + ":获取修改记录列表失败").show();
            }
        });
    }

}
