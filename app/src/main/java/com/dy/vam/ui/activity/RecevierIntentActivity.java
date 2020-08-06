package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.NoticeUrl;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.dy.vam.config.Constant.SUCCESS;

/**
 * 通知里获取Url
 * Created by James on 2018/5/18.
 */

public class RecevierIntentActivity extends BaseActivity {

    private static final String EXTRAS = "extras";

    public static void actionStart(Context context, String extras) {
        Intent intent = new Intent(context, RecevierIntentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRAS, extras);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getUrlByNoticeUrl()).getUrlByNotice(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(EXTRAS)).enqueue(new Callback<NoticeUrl>() {
            @Override
            public void onResponse(Call<NoticeUrl> call, Response<NoticeUrl> response) {
                LoadDialog.dismiss(RecevierIntentActivity.this);
                switch (response.body().getCode()) {
                    case SUCCESS:
                        if (!TextUtils.isEmpty(response.body().getData().getUrl())) {
                            WebViewActivity.actionStart(RecevierIntentActivity.this, "", response.body().getData().getUrl());
                        } else {
                            Toasty.error(RecevierIntentActivity.this, "通知地址解析失败!").show();
                        }
                        finish();
                        break;
                    default:
                        Toasty.error(RecevierIntentActivity.this, response.body().getMsg()).show();
                        finish();
                        break;
                }

            }

            @Override
            public void onFailure(Call<NoticeUrl> call, Throwable t) {
                LoadDialog.dismiss(RecevierIntentActivity.this);   Toasty.error(RecevierIntentActivity.this, getString(R.string.net_error) + "404").show();
                finish();

            }
        });
    }
}
