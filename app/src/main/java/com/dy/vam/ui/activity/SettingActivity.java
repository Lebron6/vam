package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.AppManager;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TipsDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/3/1.
 */

public class SettingActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.btn_login_out)
    Button btnLoginOut;
    private TipsDialog loginOutDialog;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, SettingActivity.class);
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
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.layout_updata_phonenum, R.id.layout_updata_password, R.id.layout_feed_back, R.id.layout_about, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_updata_phonenum:
                VerifyPhoneNumActivity.actionStart(SettingActivity.this, "验证手机号");
                break;
            case R.id.layout_updata_password:
                UpdataPasswordActivity.actionStart(SettingActivity.this, "修改密码");
                break;
            case R.id.layout_feed_back:
                FeedBackActivity.actionStart(SettingActivity.this, "意见反馈");
                break;
            case R.id.layout_about:
                AboutUsActivity.actionStart(SettingActivity.this, "关于我们");
                break;
            case R.id.btn_login_out:
                loginOut();
                break;
        }
    }

    private void loginOut() {
        btnLoginOut.setEnabled(false);
        loginOutDialog = new TipsDialog(this, "确定退出吗?", new TipsDialog.DialogClickListener() {
            @Override
            public void yes() {
                loginOutDialog.dismiss();
                LoadDialog.show(SettingActivity.this);
                createRequest(BaseUrl.getInstence().getLoginOutUrl()).loginOut(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<Verif>() {
                    @Override
                    public void onResponse(Call<Verif> call, Response<Verif> response) {
                        btnLoginOut.setEnabled(true);
                        LoadDialog.dismiss(SettingActivity.this);
                        switch (response.body().getCode()) {
                            case Constant.SUCCESS:
                                Toasty.info(SettingActivity.this, "退出成功").show();
                                PreferenceUtils.getInstance().loginOut();
                                AppManager.getAppManager().AppExit(SettingActivity.this);
//                        AppManager.getAppManager().finishAllActivity();
                                startActivity(new Intent(SettingActivity.this, GuideActivity.class));
                                break;
                            default:
                                Toasty.error(SettingActivity.this, response.body().getMsg()).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Verif> call, Throwable t) {
                        LoadDialog.dismiss(SettingActivity.this);
                        btnLoginOut.setEnabled(true);
                        Toasty.warning(SettingActivity.this, getString(R.string.net_error) + ":退出失败!").show();
                        Log.e("意见反馈失败", t.toString());
                    }
                });
            }

            @Override
            public void no() {
                btnLoginOut.setEnabled(true);
                loginOutDialog.dismiss();
            }
        });
        loginOutDialog.show();

    }

}
