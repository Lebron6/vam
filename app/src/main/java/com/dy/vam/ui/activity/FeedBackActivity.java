package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 意见反馈
 */

public class FeedBackActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.edit_feed_content)
    EditText editFeedContent;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
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
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        String msg=editFeedContent.getText().toString();
        if (TextUtils.isEmpty(msg)){
            Toasty.error(FeedBackActivity.this,"请输入您要反馈的信息").show();
            return;
        }
        commitFeedBackInfo(msg);
    }

    private void commitFeedBackInfo(String msg) {
        LoadDialog.show(this);
        btnCommit.setEnabled(false);
        createRequest(BaseUrl.getInstence().getFeedBackUrl()).feedBack(PreferenceUtils.getInstance().getUserToken(),msg).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(FeedBackActivity.this);
                btnCommit.setEnabled(true);
                LoadDialog.dismiss(FeedBackActivity.this);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(FeedBackActivity.this,"意见反馈成功").show();
                        finish();
                        break;
                    default:
                        Toasty.error(FeedBackActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                LoadDialog.dismiss(FeedBackActivity.this);
                btnCommit.setEnabled(true);
                Toasty.warning(FeedBackActivity.this, getString(R.string.net_error)+":意见反馈失败!").show();
                Log.e("意见反馈失败", t.toString());
            }
        });
    }
}
