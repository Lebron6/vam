package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.UpdatePasswordParameters;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.widget.LoadDialog;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册验证手机号码
 */

public class RegisterVerifActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_phone_code)
    TextView tvGetPhoneCode;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String account;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, RegisterVerifActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_register_verif;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.image_back, R.id.btn_next, R.id.tv_to_login, R.id.tv_get_phone_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_get_phone_code:
                account = etAccount.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    Toasty.error(RegisterVerifActivity.this, "请输入手机号").show();
                    return;
                }
                getPhoneCode();
                break;
            case R.id.btn_next:
                String phoneNum = etAccount.getText().toString();
                String code = etCode.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    Toasty.error(RegisterVerifActivity.this, "请输入手机号").show();
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    Toasty.error(RegisterVerifActivity.this, "请输入验证码").show();
                    return;
                }
                toRegister(phoneNum, code);
                break;
            case R.id.tv_to_login:
                LoginActivity.actionStart(this, "登录");
                break;
        }
    }

    private void toRegister(String phoneNum, String code) {
        btnNext.setEnabled(false);
        LoadDialog.show(RegisterVerifActivity.this);
        createRequest(BaseUrl.getInstence().getUserRegisterUrl()).userRegister(phoneNum, code).enqueue(new Callback<UpdatePasswordParameters>() {

            @Override
            public void onResponse(Call<UpdatePasswordParameters> call, Response<UpdatePasswordParameters> response) {
                btnNext.setEnabled(true);
                LoadDialog.dismiss(RegisterVerifActivity.this);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        RegisterSetPasswordActivity.actionStart(RegisterVerifActivity.this, response.body().getData().getUserid(), response.body().getData().getVerify());
                        break;
                    default:
                        Toasty.error(RegisterVerifActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<UpdatePasswordParameters> call, Throwable t) {
                LoadDialog.dismiss(RegisterVerifActivity.this);
                btnNext.setEnabled(true);
                Toasty.warning(RegisterVerifActivity.this,  getString(R.string.net_error)).show();
                Log.e("toRegisterFailure", t.toString());
            }
        });

    }

    private void getPhoneCode() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getSendMsgUrl()).sendMsg(account, Utils.getIMEI(this), "0").enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(RegisterVerifActivity.this);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        TimeCount time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                        time.start();// 开始计时
                        Toasty.info(RegisterVerifActivity.this, "请查收验证码").show();
                        break;
                    default:
                        Toasty.error(RegisterVerifActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                Toasty.warning(RegisterVerifActivity.this, "网络异常!").show();
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

            }catch (Exception e){

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
            }catch (Exception E){}

        }

    }
}
