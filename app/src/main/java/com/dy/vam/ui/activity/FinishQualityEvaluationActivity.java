package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.GetStarInfo;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.StarBarView;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/26.
 * 完成质量考评
 */

public class FinishQualityEvaluationActivity extends BaseActivity {

    private static final String TASK_ID = "task_id";
    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.tv_task_name)
    TextView tvTaskName;
    @BindView(R.id.tv_charger_name)
    TextView tvChargerName;
    @BindView(R.id.star_beauty)
    StarBarView starBeauty;
    @BindView(R.id.star_logic)
    StarBarView starLogic;
    @BindView(R.id.star_complete)
    StarBarView starComplete;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_beauty)
    TextView tvBeauty;
    @BindView(R.id.tv_logic)
    TextView tvLogic;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, String title, int taskid) {
        Intent intent = new Intent(context, FinishQualityEvaluationActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(TASK_ID, taskid);
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
        return R.layout.activity_finsh_quality_evaluation;
    }

    @Override
    protected void initViews() {
        starBeauty.setOnStarSelectedListener(beautyStarSelectedListener);
        starLogic.setOnStarSelectedListener(logicStarSelectedListener);
        starComplete.setOnStarSelectedListener(completeStarSelectedListener);
    }

    StarBarView.OnStarSelectedListener beautyStarSelectedListener = new StarBarView.OnStarSelectedListener() {
        @Override
        public void onStarSelectedListener(int num) {
//            tvBeauty.setText("美观度" + "（" + (num * 20) + "%" + "）");
        }
    };
    StarBarView.OnStarSelectedListener logicStarSelectedListener = new StarBarView.OnStarSelectedListener() {
        @Override
        public void onStarSelectedListener(int num) {
//            tvLogic.setText("逻辑性" + "（" + (num * 20) + "%" + "）");
        }
    };
    StarBarView.OnStarSelectedListener completeStarSelectedListener = new StarBarView.OnStarSelectedListener() {
        @Override
        public void onStarSelectedListener(int num) {
//            tvComplete.setText("完整性" + "（" + (num * 20) + "%" + "）");
        }
    };

    private void loadData() {
        createRequest(BaseUrl.getInstence().getExamineGetStarInfoUrl()).getStarInfo(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<GetStarInfo>() {
            @Override
            public void onResponse(Call<GetStarInfo> call, Response<GetStarInfo> response) {
                GetStarInfo getStarInfo = response.body();
                if (getStarInfo != null) {
                    if (getStarInfo.getCode() == Constant.SUCCESS) {
                        if (getStarInfo.getData() != null) {
                            tvChargerName.setText(getStarInfo.getData().getUsername());
                            tvTaskName.setText(getStarInfo.getData().getName());
                        }
                    } else {
                        Toasty.error(FinishQualityEvaluationActivity.this, getStarInfo.getMsg()).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetStarInfo> call, Throwable t) {
                Toasty.error(FinishQualityEvaluationActivity.this, getString(R.string.net_error) + ":获取质量考评页面数据").show();
            }
        });
    }

    @Override
    protected void initDatas() {
        loadData();
    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        if (starBeauty.getStarRating() == 0) {
            Toasty.warning(FinishQualityEvaluationActivity.this, "请为美观度打星").show();
            return;
        }
        if (starComplete.getStarRating() == 0) {
            Toasty.warning(FinishQualityEvaluationActivity.this, "请为完整度打星").show();
            return;
        }
        if (starLogic.getStarRating() == 0) {
            Toasty.warning(FinishQualityEvaluationActivity.this, "请为逻辑性打星").show();
            return;
        }
        commit();
    }

    private void commit() {
        btnCommit.setEnabled(false);
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getExamineAddStarUrl()).addStar(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0), (int) starBeauty.getStarRating(), (int) starLogic.getStarRating(), (int) starComplete.getStarRating()).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                btnCommit.setEnabled(true);
                LoadDialog.dismiss(FinishQualityEvaluationActivity.this);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(FinishQualityEvaluationActivity.this, "提交质量考评成功").show();
                        finish();
                        break;
                    default:
                        Toasty.error(FinishQualityEvaluationActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                LoadDialog.dismiss(FinishQualityEvaluationActivity.this);
                btnCommit.setEnabled(true);
                Toasty.warning(FinishQualityEvaluationActivity.this, getString(R.string.net_error) + ":提交质量考评失败!").show();
            }
        });
    }

}
