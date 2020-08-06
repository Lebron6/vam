package com.dy.vam.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dy.vam.api.LoggingInterceptor;
import com.dy.vam.api.VamApi;
import com.dy.vam.tools.HttpLogger;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by James on 2018/1/9.
 */

public abstract class BaseFragment extends Fragment{

    Unbinder unbinder;
    OkHttpClient mOkHttpClient;
    private Retrofit retrofit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(attachLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        loggerSimpleName();
        return view;
    }
    public void loggerSimpleName(){
        Log.d("当前所处界面 ：",getClass().getSimpleName());
    }
    /**
     * 初始化okhttpclient.
     * @return okhttpClient
     */

    public OkHttpClient okhttpclient() {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger(getClass().getSimpleName()));
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor())
                    .build();
        return mOkHttpClient;
    }

    public VamApi createRequest(String url) {
         retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                 .client(okhttpclient())
                .build();
        VamApi vamApi = retrofit.create(VamApi.class);
        return vamApi;
    }

    @Override
    public void onResume() {
        super.onResume();
        initDatas();
    }

    /**
     * 注销ButterKnife
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
        Log.e(getClass().getSimpleName(),"onDestroyView");

    }

    /**
     * 绑定布局文件
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 获取数据
     */
    protected abstract void initDatas();


}
