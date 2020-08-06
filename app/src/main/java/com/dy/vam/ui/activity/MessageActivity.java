package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Notice;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.MessageAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 消息
 */

public class MessageActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.sv_message)
    SpringView svMessage;
    @BindView(R.id.layout_data_null)
    RelativeLayout layoutDataNull;
    private Notice notice;
    private int PAGE = 1;
    private MessageAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_message;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        svMessage.setHeader(new DefaultHeader(this));
        svMessage.setFooter(new DefaultFooter(this));
        svMessage.setType(SpringView.Type.FOLLOW);
        adapter = new MessageAdapter(this);
        svMessage.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                PAGE = 1;
                loadData(Constant.REFRESH_STATUS, PAGE);
            }

            @Override
            public void onLoadmore() {
                PAGE = ++PAGE;
                if (notice.getData().isHas_next()) {
                    loadData(Constant.LOAD_MORE_STATUS, PAGE);
                } else {
                    Toasty.info(MessageActivity.this, "已加载全部数据！").show();
                    svMessage.onFinishFreshAndLoad();
                }
            }
        });

        adapter.setOnItemClickLitener(new MessageAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (!TextUtils.isEmpty(notice.getData().getList().getData().get(position).getUrl())) {

                    WebViewActivity.actionStart(MessageActivity.this, "", notice.getData().getList().getData().get(position).getUrl());
                }
            }
        });
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
        loadData(Constant.REFRESH_STATUS, PAGE);
    }

    @Override
    protected void initDatas() {
        LoadDialog.show(this);

    }

    private void loadData(final int status, final int page) {
        createRequest(BaseUrl.getInstence().getNoticeListUrl()).getNoticeList(PreferenceUtils.getInstance().getUserToken(), page).enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (svMessage != null) {
                    svMessage.onFinishFreshAndLoad();
                }
                LoadDialog.dismiss(MessageActivity.this);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        if (response.body().getData() != null && response.body().getData().getList() != null && response.body().getData().getList().getData().size() > 0) {
                            layoutDataNull.setVisibility(View.GONE);
                            if (status == Constant.REFRESH_STATUS) {
                                notice = response.body();
                                adapter.setDatas(notice);
                                RecyclerViewHelper.initRecyclerViewV(MessageActivity.this, rvMessage, false, adapter);
                            } else {
                                notice.getData().setHas_next(response.body().getData().isHas_next());
                                notice.getData().getList().getData().addAll(response.body().getData().getList().getData());
                                adapter.setDatas(notice);
                            }
                        } else {
                            layoutDataNull.setVisibility(View.VISIBLE);
                            Toasty.info(MessageActivity.this, "暂无更多数据").show();
                        }
                        break;
                    default:
                        Toasty.error(MessageActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                LoadDialog.dismiss(MessageActivity.this);
                if (svMessage != null) {
                    svMessage.onFinishFreshAndLoad();
                }
                layoutDataNull.setVisibility(View.VISIBLE);
//                Toasty.error(MessageActivity.this, getString(R.string.net_error) + ":获取消息列表失败！").show();
            }
        });
    }

}
