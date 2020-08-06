package com.dy.vam.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.OperaList;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.adapter.ViewPagerAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.NavitationScrollCenterLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/2/6.
 */

public class Opera2Fragment extends BaseFragment {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.tab_opera)
    NavitationScrollCenterLayout tabOpera;
    @BindView(R.id.pager_opera)
    ViewPager pagerOpera;
    private ViewPagerAdapter viewPagerAdapter;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    public static final String REFALSH = "refalsh";

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_opera_three;
    }

    @Override
    protected void initViews() {

    }

    public void loadDatas() {
        LoadDialog.show(getActivity());
        createRequest(BaseUrl.getInstence().getOperationListUrl()).getOperationList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<OperaList>() {
            @Override
            public void onResponse(Call<OperaList> call, Response<OperaList> response) {
                LoadDialog.dismiss(getActivity());
                OperaList operaList = response.body();
                if (response != null && operaList != null) {
                    switch (operaList.getCode()) {
                        case Constant.SUCCESS:
                            titles.clear();
                            fragments.clear();
                            if (operaList.getData() != null) {
                                if (operaList.getData().getOperation() != null) {
                                    titles.add("工作台");
                                    fragments.add(new OperaChildFragment(0, operaList));
                                }
                                if (operaList.getData().getManage() != null) {
                                    titles.add("管理员");
                                    fragments.add(new OperaChildFragment(1, operaList));
                                }
                                if (operaList.getData().getView() != null) {
                                    titles.add("VIP");
                                    fragments.add(new OperaChildFragment(2, operaList));
                                }
                                viewPagerAdapter = new ViewPagerAdapter(Opera2Fragment.this.getFragmentManager(), fragments);
                                pagerOpera.setAdapter(viewPagerAdapter);
                                pagerOpera.setOffscreenPageLimit(2);
                                tabOpera.setViewPager(getActivity(), titles, pagerOpera, R.color.white, R.color.colorTextTheme, 16, 16, 0, true, R.color.white, 0f, 15f, 15f, 100);
                                tabOpera.setBgLine(getActivity(), 1, R.color.colorAccent);
                                tabOpera.setNavLine(getActivity(), 3, R.color.colorTextTheme, 0);
                            } else {
                                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取操作标签失败！").show();
                            }
                            break;
                        default:
                            Toasty.error(getActivity(), operaList.getMsg()).show();
                            break;
                    }
                } else {
                    Toasty.error(getActivity(), getString(R.string.net_error) + ":获取操作标签失败！").show();
                }
            }

            @Override
            public void onFailure(Call<OperaList> call, Throwable t) {
                LoadDialog.dismiss(getActivity());
                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取操作标签失败！").show();
                Log.e("获取操作标签", t.toString());
            }
        });
    }

    @Override
    protected void initDatas() {
        loadDatas();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }

    }

}
