package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.SortModelInfo;
import com.dy.vam.entity.UpLoadReimbursementPicResult;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.AndroidInterface;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.PromptDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import com.dy.vam.ui.widget.TipsDialog;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by James on 2018/1/15.
 * 最新动态、
 */

public class WebViewActivity extends BaseActivity {

    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String CLOSE_WINDOW = "tag";

    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_question)
    LinearLayout layoutQuestion;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    AgentWeb mAgentWeb;
    @BindView(R.id.layout_commit)
    LinearLayout layoutCommit;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title,String url){
        actionStart(context,title,url,false);
    }
    /**
     * @param context
     * @param title
     * @param url
     * @param tag 用于确认是否直接响应closeWindow
     */
    public static void actionStart(Context context, String title, String url,boolean tag) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(URL, url);
        intent.putExtra(CLOSE_WINDOW, tag);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_webview;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initViews() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            WebView.setWebContentsDebuggingEnabled(true);
        }
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .setIndicatorColor(getResources().getColor(R.color.colorTextTheme))
                .setSecurityType(AgentWeb.SecurityType.strict) //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(getUrl());
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(mAgentWeb, this, layoutCommit));

    }

    protected ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title.length() > 10)
                title = title.substring(0, 10) + "...";
            toolbarTitle.setText(title);
        }
    };

    protected WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //  super.onProgressChanged(view, newProgress);
        }

        //设置响应js 的Confirm()函数
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            TipsDialog dialog=new TipsDialog(WebViewActivity.this, message, new TipsDialog.DialogClickListener() {
                @Override
                public void yes() {
                    result.confirm();
                }

                @Override
                public void no() {
                    result.cancel();
                }
            });
            dialog.show();
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
            PromptDialog promptDialog=new PromptDialog(WebViewActivity.this, new PromptDialog.DialogClickListener() {
                @Override
                public void yes(String msg) {
                    result.confirm(msg);
                }

                @Override
                public void no() {
                    result.cancel();
                }
            });
            promptDialog.show();
            return true;
        }
    };

    protected WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl() + "");
        }

        //
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            //intent:// scheme的处理 如果返回false ， 则交给 DefaultWebClient 处理 ， 默认会打开该Activity  ， 如果Activity不存在则跳到应用市场上去.  true 表示拦截
            //例如优酷视频播放 ，intent://play?...package=com.youku.phone;end;
            //优酷想唤起自己应用播放该视频 ， 下面拦截地址返回 true  则会在应用内 H5 播放 ，禁止优酷唤起播放该视频， 如果返回 false ， DefaultWebClient  会根据intent 协议处理 该地址 ， 首先匹配该应用存不存在 ，如果存在 ， 唤起该应用播放 ， 如果不存在 ， 则跳到应用市场下载该应用 .
            if (url.startsWith("intent://") && url.contains("com.youku.phone"))
                return true;
            /*else if (isAlipay(view, url))   //1.2.5开始不用调用该方法了 ，只要引入支付宝sdk即可 ， DefaultWebClient 默认会处理相应url调起支付宝
                return true;*/
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

//            if (url.equals(getUrl())) {
//                pageNavigator(View.GONE);
//            } else {
//                pageNavigator(View.VISIBLE);
//            }
        }
    };

    public String getUrl() {
        if (!TextUtils.isEmpty(getIntent().getStringExtra(URL))) {
            return getIntent().getStringExtra(URL);
        }
        return "http://www.baidu.com";
    }

    private void pageNavigator(int tag) {
        layoutBack.setVisibility(tag);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
    }

    @Override
    protected void initDatas() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.REQUEST_CODE:
                if (data != null) {
                    SortModelInfo sortModelInfo = (SortModelInfo) data.getSerializableExtra("user");
                    Log.e("选中人员", sortModelInfo.toString());
                    mAgentWeb.getJsEntraceAccess().quickCallJs("confirm_user", sortModelInfo.getId() + "", sortModelInfo.getName(), sortModelInfo.getIconUrl(), sortModelInfo.getPost());
                } else {
                    Toasty.warning(WebViewActivity.this, "未指定人员").show();
                }
                break;
            case Constant.REQUEST_IMAGE:
                if (data != null) {
                    List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    upLoadImg(path);
                }
                break;
        }
    }

    private void upLoadImg(List<String> path) {
        Toasty.normal(WebViewActivity.this, "上传中,请稍等...").show();
        for (int i = 0; i < path.size(); i++) {
            LoadDialog.show(WebViewActivity.this);
            createRequest(BaseUrl.getInstence().getUploadImgUrl()).getUpLoadReimbursementPicResult(PreferenceUtils.getInstance().getUserToken(), Utils.bitmapToBase64(Utils.getImage(path.get(i))), 1).enqueue(new Callback<UpLoadReimbursementPicResult>() {
                @Override
                public void onResponse(Call<UpLoadReimbursementPicResult> call, Response<UpLoadReimbursementPicResult> response) {
                    LoadDialog.dismiss(WebViewActivity.this);
                    if (response != null && response.body() != null) {
                        if (response.body().getCode() == Constant.SUCCESS) {
                            List<UpLoadReimbursementPicResult.DataBean> list = new ArrayList<>();
                            list.add(response.body().getData());
                            String json = new Gson().toJson(list);
                            mAgentWeb.getJsEntraceAccess().quickCallJs("add_img", json);
                            Log.e("add_img", json);
                            LoadDialog.dismiss(WebViewActivity.this);
                        }
                    } else {
                        Toasty.error(WebViewActivity.this, "图片上传回调为空").show();
                    }
                }

                @Override
                public void onFailure(Call<UpLoadReimbursementPicResult> call, Throwable t) {
                    LoadDialog.dismiss(WebViewActivity.this);
                    Log.e("图片上传异常", t.toString());
                    Toasty.error(WebViewActivity.this, "图片上传异常").show();
                }
            });
        }

    }


    @OnClick({R.id.layout_back, R.id.layout_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                if (getIntent().getBooleanExtra(CLOSE_WINDOW,false)==true){
                    finish();
                }else{
                    if (!mAgentWeb.back()) {
                        finish();
                    }else {

                        mAgentWeb.clearWebCache();

                    }

                }

                break;
            case R.id.layout_commit:
                mAgentWeb.getJsEntraceAccess().quickCallJs("save");
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (getIntent().getBooleanExtra(CLOSE_WINDOW,false)==true){
                finish();
            }else{
                if (!mAgentWeb.back()) {
                    finish();
                }else {
                    mAgentWeb.clearWebCache();
                }

            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }


}
