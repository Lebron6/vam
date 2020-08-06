package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.ConfirmTaskOver;
import com.dy.vam.entity.FinshTaskExamine;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.SUCCESS;

/**
 * Created by James on 2018/1/26.
 * 任务完成，可提示修改，或审批任务完成
 */

public class ExamineTaskOverActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String TASK_ID = "taskid";
    @BindView(R.id.tv_task_name)
    TextView tvTaskName;
    @BindView(R.id.tv_finish_time)
    TextView tvFinishTime;
    @BindView(R.id.tv_distribution_integral)
    TextView tvDistributionIntegral;
    @BindView(R.id.tv_final_integral)
    TextView tvFinalIntegral;
    @BindView(R.id.et_tips)
    EditText etTips;
    @BindView(R.id.btn_ea_succ)
    Button btnEaSucc;
    @BindView(R.id.btn_tips_updata)
    Button btnTipsUpdata;
    @BindView(R.id.tv_see_updata_list)
    TextView tvSeeUpdataList;
    private TitleBarManger manger;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, String title, int taskid) {
        Intent intent = new Intent(context, ExamineTaskOverActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(TASK_ID, taskid);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
//        manger.setQuestion("");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_examine_task_over;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        loadDatas();
    }

    private void loadDatas() {
        createRequest(BaseUrl.getInstence().getExamineGetFinishTaskExamineUrl()).getFinshTaskExamineData(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<FinshTaskExamine>() {
            @Override
            public void onResponse(Call<FinshTaskExamine> call, Response<FinshTaskExamine> response) {
                if (response.body() != null) {
                    switch (response.body().getCode()) {
                        case SUCCESS:
                            if (response.body().getData() != null) {
                                manger.setTitle(response.body().getData().getTitle());
                                tvTaskName.setText(response.body().getData().getName());
                                tvDistributionIntegral.setText(response.body().getData().getIntegration());
                                tvFinalIntegral.setText(response.body().getData().getFinal_integration());
                                tvFinishTime.setText(response.body().getData().getFinishtime());
                                btnTipsUpdata.setText("提示修改"+response.body().getData().getIteration());
                            }
                            break;
                        default:
                            Toasty.error(ExamineTaskOverActivity.this, response.body().getMsg()).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<FinshTaskExamine> call, Throwable t) {

            }
        });
    }


    @OnClick({R.id.btn_ea_succ, R.id.btn_tips_updata, R.id.tv_see_updata_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ea_succ:
                commit();
                break;
            case R.id.btn_tips_updata:
                if (TextUtils.isEmpty(etTips.getText().toString())){
                    Toasty.error(ExamineTaskOverActivity.this,"请输入您的提示").show();
                    return;
                }
                tips();
                break;
            case R.id.tv_see_updata_list:
                ModifyTheRecordActivity.actionStart(ExamineTaskOverActivity.this, "修改记录",getIntent().getIntExtra(TASK_ID, 0));
                break;
        }
    }

    private void tips() {
        btnTipsUpdata.setEnabled(false);
        LoadDialog.show(ExamineTaskOverActivity.this);
        createRequest(BaseUrl.getInstence().getExamineAddIterationUrl()).tips(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0),etTips.getText().toString()).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(ExamineTaskOverActivity.this);
                btnTipsUpdata.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(ExamineTaskOverActivity.this, "提示修改成功").show();
                        finish();
                        break;
                    default:
                        Toasty.error(ExamineTaskOverActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnTipsUpdata.setEnabled(true);
                LoadDialog.dismiss(ExamineTaskOverActivity.this);
                Toasty.warning(ExamineTaskOverActivity.this, getString(R.string.net_error)).show();
            }
        });
    }

    private void commit() {
        btnEaSucc.setEnabled(false);
        LoadDialog.show(ExamineTaskOverActivity.this);
        createRequest(BaseUrl.getInstence().getExamineCinfirmTaskFinishUrl()).confirmTaskFinish(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<ConfirmTaskOver>() {
            @Override
            public void onResponse(Call<ConfirmTaskOver> call, Response<ConfirmTaskOver> response) {
                LoadDialog.dismiss(ExamineTaskOverActivity.this);
                btnEaSucc.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(ExamineTaskOverActivity.this, "审批成功").show();
                        if (response.body().getData().getStar()==0){
                            finish();
                        }else{
                            FinishQualityEvaluationActivity.actionStart(ExamineTaskOverActivity.this, "完成质量考评",getIntent().getIntExtra(TASK_ID, 0));
finish();
//                                   TaskOverActivity.actionStart(ExamineTaskOverActivity.this,getIntent().getIntExtra(TASK_ID, 0));
                        }
                        break;
                    default:
                        Toasty.error(ExamineTaskOverActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<ConfirmTaskOver> call, Throwable t) {
                btnEaSucc.setEnabled(true);
                LoadDialog.dismiss(ExamineTaskOverActivity.this);
                Toasty.warning(ExamineTaskOverActivity.this, getString(R.string.net_error)).show();
            }
        });
    }
}
