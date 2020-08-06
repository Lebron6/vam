package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.FinancialGetCustomList;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.activity.BusinessDetailsActivity;
import com.dy.vam.ui.adapter.BusinessPromotionAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import butterknife.BindView;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/16.
 * 业务提成录入
 */

public class BusinessPromotionFragment extends BaseFragment {
    @BindView(R.id.rv_business_promotion)
    RecyclerView rvBusinessPromotion;
    @BindView(R.id.sv_promotion)
    SpringView svPromotion;
    Unbinder unbinder;
    @BindView(R.id.image_null)
    ImageView imageNull;
    @BindView(R.id.layout_data_null)
    RelativeLayout layoutDataNull;
    private int type;
    private BusinessPromotionAdapter adapter;
    private FinancialGetCustomList financialGetCustomList;
    private int page = 1;

    public BusinessPromotionFragment(int type) {
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_business_promotion;
    }

    @Override
    protected void initViews() {
        svPromotion.setHeader(new DefaultHeader(getActivity()));
        svPromotion.setFooter(new DefaultFooter(getActivity()));
        svPromotion.setType(SpringView.Type.FOLLOW);
        adapter = new BusinessPromotionAdapter(getActivity());
        svPromotion.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData(Constant.REFRESH_STATUS, page);
            }

            @Override
            public void onLoadmore() {
                page = ++page;
                if (financialGetCustomList.getData().isHas_next()) {
                    loadData(Constant.LOAD_MORE_STATUS, page);
                } else {
                    Toasty.info(getActivity(), "已加载全部数据！").show();
                    svPromotion.onFinishFreshAndLoad();
                }
            }
        });
        adapter.setOnItemClickLitener(new BusinessPromotionAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                BusinessDetailsActivity.actionStart(getActivity(), financialGetCustomList.getData().getList().get(position).getCustomid());
            }
        });
    }

    @Override
    protected void initDatas() {
        LoadDialog.show(getActivity());
        loadData(Constant.REFRESH_STATUS, page);
    }

    private void loadData(final int status, final int page) {
        createRequest(BaseUrl.getInstence().getFinancialCustomListUrl()).financialGetCustomList(PreferenceUtils.getInstance().getUserToken(), type, page).enqueue(new Callback<FinancialGetCustomList>() {
            @Override
            public void onResponse(Call<FinancialGetCustomList> call, Response<FinancialGetCustomList> response) {
                if (svPromotion != null) {
                    svPromotion.onFinishFreshAndLoad();
                }
                LoadDialog.dismiss(getActivity());
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        if (response.body().getData() != null && response.body().getData().getList() != null && response.body().getData().getList().size() > 0) {
                            layoutDataNull.setVisibility(View.GONE);
                            if (status == Constant.REFRESH_STATUS) {
                                financialGetCustomList = response.body();
                                adapter.setDatas(financialGetCustomList);
                                RecyclerViewHelper.initRecyclerViewV(getActivity(), rvBusinessPromotion, false, adapter);
                            } else {
                                financialGetCustomList.getData().setHas_next(response.body().getData().isHas_next());
                                financialGetCustomList.getData().getList().addAll(response.body().getData().getList());
                                adapter.setDatas(financialGetCustomList);
                            }
                        } else {
                            layoutDataNull.setVisibility(View.VISIBLE);
//                            Toasty.info(getActivity(), "暂无更多数据").show();
                        }
                        break;
                    default:
                        Toasty.error(getActivity(), response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<FinancialGetCustomList> call, Throwable t) {
                LoadDialog.dismiss(getActivity());
                if (svPromotion != null) {
                    svPromotion.onFinishFreshAndLoad();
                }
                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取业务提成客户列表失败！").show();
                Log.e("获取业务提成客户列表", t.toString());
            }
        });
    }

}
