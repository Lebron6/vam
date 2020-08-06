package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.GetTaskOverInfo;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.TaskTrackAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.StarBarView;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.SUCCESS;

/**
 * Created by James on 2018/1/26.
 * 任务完成
 */

public class TaskOverActivity extends BaseActivity {

    private static final String TASK_ID = "task_id";
    private static final String TITLE = "title";
    @BindView(R.id.tv_task_name)
    TextView tvTaskName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_distribution_integral)
    TextView tvDistributionIntegral;
    @BindView(R.id.tv_final_integral)
    TextView tvFinalIntegral;
    @BindView(R.id.rv_task_track)
    RecyclerView rvTaskTrack;
    @BindView(R.id.tv_beauty)
    TextView tvBeauty;
    @BindView(R.id.star_beauty)
    StarBarView starBeauty;
    @BindView(R.id.tv_logic)
    TextView tvLogic;
    @BindView(R.id.star_logic)
    StarBarView starLogic;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.star_complete)
    StarBarView starComplete;
    @BindView(R.id.layout_star)
    LinearLayout layoutStar;
    @BindView(R.id.image_ok)
    ImageView imageOk;
    private TitleBarManger manger;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, int taskid,String title) {
        Intent intent = new Intent(context, TaskOverActivity.class);
        intent.putExtra(TASK_ID, taskid);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(TITLE));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_task_over;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        loadData();
    }

    private void loadData() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getExamineGetTaskUrl()).getTaskOverInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<GetTaskOverInfo>() {
            @Override
            public void onResponse(Call<GetTaskOverInfo> call, Response<GetTaskOverInfo> response) {
                LoadDialog.dismiss(TaskOverActivity.this);
                GetTaskOverInfo taskOverInfo = response.body();
                if (taskOverInfo != null) {
                    if (taskOverInfo.getCode() == SUCCESS) {
                        if (taskOverInfo.getData() != null) {
                            if (taskOverInfo.getData().getFinish_status()==2){
                                imageOk.setVisibility(View.VISIBLE);
                            }else{
                                imageOk.setVisibility(View.GONE);
                            }
                            manger.setTitle(taskOverInfo.getData().getTitle());
                            tvTaskName.setText(taskOverInfo.getData().getName());
                            tvTime.setText(taskOverInfo.getData().getFinishtime());
                            tvFinalIntegral.setText(taskOverInfo.getData().getFinal_integration());
                            tvDistributionIntegral.setText(taskOverInfo.getData().getIntegration());
                            if (taskOverInfo.getData().getStar()!=null&&taskOverInfo.getData().getStar().getStar1()!=null&&taskOverInfo.getData().getStar().getStar2()!=null&&taskOverInfo.getData().getStar().getStar3()!=null) {
                                layoutStar.setVisibility(View.VISIBLE);
//                                tvBeauty.setText("美观度" + "（" + (Integer.valueOf(taskOverInfo.getData().getStar().getStar1()) * 20) + "%" + "）");
//                                tvLogic.setText("逻辑性" + "（" + (Integer.valueOf(taskOverInfo.getData().getStar().getStar2()) * 20) + "%" + "）");
//                                tvComplete.setText("完整性" + "（" + (Integer.valueOf(taskOverInfo.getData().getStar().getStar3()) * 20) + "%" + "）");
                                starBeauty.setStarRating(Integer.valueOf(taskOverInfo.getData().getStar().getStar1()));
                                starLogic.setStarRating(Integer.valueOf(taskOverInfo.getData().getStar().getStar2()));
                                starComplete.setStarRating(Integer.valueOf(taskOverInfo.getData().getStar().getStar3()));
                            } else {
                                layoutStar.setVisibility(View.GONE);
                            }
                            int count=0;    //count为Item首部1+中间Size+尾部Size   （1+N+1）
                            if (taskOverInfo.getData().getFinishtask()!=null&&taskOverInfo.getData().getFinishtask().size()>0){
                                count=count+1;
                            }
                            if (taskOverInfo.getData().getIteration()!=null&&taskOverInfo.getData().getIteration().size()>0){
                                count=count+taskOverInfo.getData().getIteration().size();
                            }
                            if (taskOverInfo.getData().getNewtask()!=null&&taskOverInfo.getData().getNewtask().size()>0){
                                count=count+1;
                            }
                            TaskTrackAdapter adapter = new TaskTrackAdapter(TaskOverActivity.this);
                            adapter.setDatas(taskOverInfo,count,taskOverInfo.getData().getFinishtask().size()>0);
                            RecyclerViewHelper.initRecyclerViewV(TaskOverActivity.this, rvTaskTrack, false, adapter);
                        }
                    } else {
                        Toasty.error(TaskOverActivity.this, taskOverInfo.getMsg()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTaskOverInfo> call, Throwable t) {
                LoadDialog.dismiss(TaskOverActivity.this);
            }
        });
    }

}
