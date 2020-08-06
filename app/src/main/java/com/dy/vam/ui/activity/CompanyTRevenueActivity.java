package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.SummaryDataDetails;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.adapter.IEDetailsAdapter;
import com.dy.vam.ui.widget.TitleBarManger;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 公司收入明细
 */

public class CompanyTRevenueActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    public static final String YEARS = "years";
    public static final String TYPE = "type";
    @BindView(R.id.rv_ie_details)
    RecyclerView rvIeDetails;
    private IEDetailsAdapter adapter;
    private TitleBarManger manger;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_company_total_revenue;
    }

    public static void actionStart(Context context, String title,int[] years,int type) {
        Intent intent = new Intent(context, CompanyTRevenueActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(YEARS, years);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }
    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, CompanyTRevenueActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
//        intent.putExtra(YEARS, years);
//        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        adapter = new IEDetailsAdapter(CompanyTRevenueActivity.this);
    }

    @Override
    protected void initTitle() {
        manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
    }

    @Override
    protected void initDatas() {
        createRequest(BaseUrl.getInstence().getSummaryGetDataDetailUrl()).getSummaryGetDataDetail(PreferenceUtils.getInstance().getUserToken(),PreferenceUtils.getInstance().getDefaultYear(),getIntent().getIntExtra(TYPE,0)).enqueue(new Callback<SummaryDataDetails>() {
            @Override
            public void onResponse(Call<SummaryDataDetails> call, final Response<SummaryDataDetails> response) {
                if (response.body().getCode()==Constant.SUCCESS){
                    if (response.body().getData()!=null&&response.body().getData().getList().size()>0){
                        adapter.setDatas(response.body());
                        RecyclerViewHelper.initRecyclerViewV(CompanyTRevenueActivity.this,rvIeDetails,false,adapter);
                        manger.setQuestion(response.body().getData().getHelp_url());
                        adapter.setOnItemClickLitener(new IEDetailsAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                CustomersTIDetailsActivity.actionStart(CompanyTRevenueActivity.this,response.body().getData().getList().get(position).getTitle(),getIntent().getIntArrayExtra(YEARS),response.body().getData().getList().get(position).getTermid());
                            }
                        });
                    }else{
                        Toasty.error(CompanyTRevenueActivity.this,"暂无列表信息").show();
                    }
                }else{
                    Toasty.error(CompanyTRevenueActivity.this,response.body().getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<SummaryDataDetails> call, Throwable t) {
                Toasty.error(CompanyTRevenueActivity.this, getString(R.string.net_error) + ":获取存续客户总收入明细失败").show();
                Log.e("存续客户总收入明细", t.toString());
            }
        });
    }

}
