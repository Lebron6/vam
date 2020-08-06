package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.GetPhoneNum;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 验证手机号
 */

public class VerifyPhoneNumActivity extends BaseActivity {

    @BindView(R.id.et_old_phone_num)
    EditText etOldPhoneNum;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_phone_code)
    TextView tvGetPhoneCode;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String account;

    public static final String DETAILS_VALUES = "title";

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, VerifyPhoneNumActivity.class);
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
        return R.layout.activity_verify_phone_num;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {
        createRequest(BaseUrl.getInstence().getPhoneUrl()).getNowPhoneNum(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<GetPhoneNum>() {
            @Override
            public void onResponse(Call<GetPhoneNum> call, Response<GetPhoneNum> response) {
                if (response.body().getCode() == Constant.SUCCESS) {
                    account = response.body().getData().getPhone_actual();
                    etOldPhoneNum.setText(response.body().getData().getPhone_view());
                } else {
                    Toasty.error(VerifyPhoneNumActivity.this, response.body().getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<GetPhoneNum> call, Throwable t) {
                Log.e("获取当前手机号码失败", t.toString());
                Toasty.error(VerifyPhoneNumActivity.this, getString(R.string.net_error) + ":获取当前手机号码失败!").show();
            }
        });
    }

    @OnClick({R.id.btn_next, R.id.tv_get_phone_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_phone_code:
                if (TextUtils.isEmpty(account)) {
                    Toasty.error(VerifyPhoneNumActivity.this, "获取验证码手机号为空").show();
                    return;
                }
                getPhoneCode();
                break;
            case R.id.btn_next:
                String code = etCode.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    Toasty.error(VerifyPhoneNumActivity.this, "请输入验证码").show();
                    return;
                }
                toVerifyPhone(code);
                break;
        }
    }

    private void toVerifyPhone(String code) {
        btnNext.setEnabled(false);
        LoadDialog.show(VerifyPhoneNumActivity.this);
        createRequest(BaseUrl.getInstence().getVerifyPhoneUrl()).verifyPhone(PreferenceUtils.getInstance().getUserToken(), code).enqueue(new Callback<Verif>() {

            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                Log.e("onResponse：", response.body().toString());
                btnNext.setEnabled(true);
                LoadDialog.dismiss(VerifyPhoneNumActivity.this);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        UpdataPhoneNumActivity.actionStart(VerifyPhoneNumActivity.this, "更换手机号码");
                        finish();
                        break;
                    default:
                        Toasty.error(VerifyPhoneNumActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                LoadDialog.dismiss(VerifyPhoneNumActivity.this);
                btnNext.setEnabled(true);
                Toasty.warning(VerifyPhoneNumActivity.this, getString(R.string.net_error)).show();
                Log.e("toRegisterFailure", t.toString());
            }
        });

    }

    private void getPhoneCode() {

        createRequest(BaseUrl.getInstence().getSendMsgUrl()).sendMsg(account, Utils.getIMEI(this), "2").enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                        time.start();// 开始计时
                        Toasty.info(VerifyPhoneNumActivity.this, "请查收验证码").show();
                        break;
                    default:
                        Toasty.error(VerifyPhoneNumActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                Toasty.warning(VerifyPhoneNumActivity.this, "网络异常!").show();
                Log.e("getPhoneCodeFailure", t.toString());
            }
        });
    }


    // /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // 计时过程显示
            try {
                tvGetPhoneCode.setClickable(false);
                tvGetPhoneCode.setText(millisUntilFinished / 1000 + "秒");
            } catch (Exception e) {

            }

        }

        @Override
        public void onFinish() {// 计时完毕时触发
            try {
                tvGetPhoneCode.setText("重新验证");
                tvGetPhoneCode.setClickable(true);
                tvGetPhoneCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPhoneCode();
                    }
                });
            } catch (Exception e) {

            }
        }

    }
}
