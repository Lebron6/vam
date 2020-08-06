package com.dy.vam.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.ExamineApprove;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.ui.activity.ExamineTaskOverActivity;
import com.dy.vam.ui.activity.NewTaskToBeEApprovedActivity;
import com.dy.vam.ui.activity.TaskOverActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.adapter.ExaminationApprovalAdapter;
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

import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_CC;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_COST;
import static com.dy.vam.config.Constant.EXAMINE_APPROVE_TYPE_TASK;

/**
 * Created by James on 2018/1/16.
 * 审批列表
 */

public class ExamineApproveListFragment extends BaseFragment {
    @BindView(R.id.rv_examine_approve)
    RecyclerView rvExamineApprove;
    Unbinder unbinder;
    @BindView(R.id.sv_examine_approve)
    SpringView svExamineApprove;
    @BindView(R.id.layout_data_null)
    RelativeLayout layoutDataNull;
    private int type;  //标记属于费用审批、任务审批、或抄送
    private int tag;    //标记属于 审批中、全部
    private int page = 1;
    private ExaminationApprovalAdapter adapter;
    private ExamineApprove examineApprove;

    public ExamineApproveListFragment(int type, int tag) {
        this.type = type;
        this.tag = tag;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_examine_approve_list;
    }


    @Override
    public void onResume() {
        super.onResume();
        loadDatas(Constant.REFRESH_STATUS, page);
    }

    @Override
    protected void initViews() {
        svExamineApprove.setHeader(new DefaultHeader(getActivity()));
        svExamineApprove.setFooter(new DefaultFooter(getActivity()));
        svExamineApprove.setType(SpringView.Type.FOLLOW);
        adapter = new ExaminationApprovalAdapter(getActivity(),type);

        svExamineApprove.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadDatas(Constant.REFRESH_STATUS, page);
            }

            @Override
            public void onLoadmore() {
                page = ++page;
                if (examineApprove.getData().isHas_next()) {
                    loadDatas(Constant.LOAD_MORE_STATUS, page);
                } else {
                    Toasty.info(getActivity(), "已加载全部数据！").show();
                    svExamineApprove.onFinishFreshAndLoad();
                }
            }
        });

        adapter.setOnItemClickLitener(new ExaminationApprovalAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (type) {
                    case EXAMINE_APPROVE_TYPE_COST:
                        WebViewActivity.actionStart(getActivity(), "费用报销", examineApprove.getData().getList().get(position).getUrl());
                        break;
                    case EXAMINE_APPROVE_TYPE_TASK:
                        switch (examineApprove.getData().getList().get(position).getExamine()) {
                            case 0:     //跳转新增任务待审批
                                NewTaskToBeEApprovedActivity.actionStart(getActivity(), examineApprove.getData().getList().get(position).getTitle(), examineApprove.getData().getList().get(position).getTaskid());
                                break;
                            case 1:     //跳转完成任务审批
                                ExamineTaskOverActivity.actionStart(getActivity(), examineApprove.getData().getList().get(position).getTitle(),examineApprove.getData().getList().get(position).getTaskid());
                                break;
                            default:
                                TaskOverActivity.actionStart(getActivity(),examineApprove.getData().getList().get(position).getTaskid(),examineApprove.getData().getList().get(position).getTitle());
                                break;
                        }
                        break;
                    case EXAMINE_APPROVE_TYPE_CC:
                        WebViewActivity.actionStart(getActivity(), "查看抄送", examineApprove.getData().getList().get(position).getUrl());
                        break;
                }
            }
        });
    }


    @Override
    protected void initDatas() {

    }

    private void loadDatas(final int status, final int page) {
        switch (type) {
            case Constant.EXAMINE_APPROVE_TYPE_COST:
                createRequest(BaseUrl.getInstence().getEamineListUrl()).examineGetExamineList(PreferenceUtils.getInstance().getUserToken(), tag, page).enqueue(new ResultCall(status));
                break;
            case Constant.EXAMINE_APPROVE_TYPE_TASK:
                createRequest(BaseUrl.getInstence().getExamineGetTaskListUrl()).examineGetExamineList(PreferenceUtils.getInstance().getUserToken(), tag, page).enqueue(new ResultCall(status));
                break;
            case Constant.EXAMINE_APPROVE_TYPE_CC:
                createRequest(BaseUrl.getInstence().getExamineGetCopyMeListUrl()).examineGetCopyMeList(PreferenceUtils.getInstance().getUserToken(), page).enqueue(new ResultCall(status));
                break;
        }
    }


    class ResultCall implements Callback<ExamineApprove> {

        private int status;

        public ResultCall(int status) {
            this.status = status;
        }

        @Override
        public void onResponse(Call<ExamineApprove> call, Response<ExamineApprove> response) {
            if (svExamineApprove != null) {
                svExamineApprove.onFinishFreshAndLoad();
            }
            LoadDialog.dismiss(getActivity());
            switch (response.body().getCode()) {
                case Constant.SUCCESS:
                    if (response.body().getData() != null && response.body().getData().getList() != null && response.body().getData().getList().size() > 0) {
                        layoutDataNull.setVisibility(View.GONE);
                        if (status == Constant.REFRESH_STATUS) {
                            examineApprove = response.body();
                            adapter.setDatas(examineApprove);
                            RecyclerViewHelper.initRecyclerViewV(getActivity(), rvExamineApprove, false, adapter);
                        } else {
                            examineApprove.getData().setHas_next(response.body().getData().isHas_next());
                            examineApprove.getData().getList().addAll(response.body().getData().getList());
                            adapter.setDatas(examineApprove);
                        }
                    } else {
                        layoutDataNull.setVisibility(View.VISIBLE);
//                        Toasty.info(getActivity(), "暂无更多数据").show();
                    }
                    break;
                default:
                    Toasty.error(getActivity(), response.body().getMsg()).show();
                    break;
            }
        }

        @Override
        public void onFailure(Call<ExamineApprove> call, Throwable t) {
            LoadDialog.dismiss(getActivity());
            if (svExamineApprove != null) {
                svExamineApprove.onFinishFreshAndLoad();
            }
            Toasty.error(getActivity(), getString(R.string.net_error) + ":获取审批列表失败！").show();
        }
    }

}
