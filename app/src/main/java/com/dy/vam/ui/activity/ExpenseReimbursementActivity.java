package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.EmailEean;
import com.dy.vam.entity.ExpenseReimbursement;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.RecyclerViewHelper;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.ExpenseReimbursementAdapter;
import com.dy.vam.ui.widget.EmailTipDialog;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 费用报销
 */

public class ExpenseReimbursementActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.rv_expense_reimbursement)
    RecyclerView rvExpenseReimbursement;
    @BindView(R.id.sv_reimbursement)
    SpringView svReimbursement;
    @BindView(R.id.layout_data_null)
    RelativeLayout layoutDataNull;
    @BindView(R.id.btn_add)
    ImageButton btnAdd;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.layout_commit)
    LinearLayout layoutCommit;


    private boolean flag; //标记右上角按钮转态
    private ExpenseReimbursementAdapter adapter;
    private int page = 1;
    private ExpenseReimbursement expenseReimbursement;
    private ExpenseReimbursement reimbursement;
    private EmailEean emailEean;
    private String idStrContent = "";
    private String email;
    private EmailTipDialog dialog;
    private String educeExcel;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .statusBarColor(R.color.colorWhite).init();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_expense_reimbursement;
    }

    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, ExpenseReimbursementActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {

        tvRightTitle.setText("选择");
//        layoutCommit.setVisibility(View.VISIBLE);
        layoutCommit.setVisibility(View.GONE);

        email = PreferenceUtils.getInstance().getEmail();
        adapter = new ExpenseReimbursementAdapter(this);
        svReimbursement.setHeader(new DefaultHeader(this));
        svReimbursement.setFooter(new DefaultFooter(this));
        svReimbursement.setType(SpringView.Type.FOLLOW);
        svReimbursement.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData(Constant.REFRESH_STATUS, page);
            }

            @Override
            public void onLoadmore() {
                page = ++page;
                if (expenseReimbursement != null && expenseReimbursement.getData() != null && expenseReimbursement.getData().isHas_next()) {
                    loadData(Constant.LOAD_MORE_STATUS, page);
                } else {
                    Toasty.info(ExpenseReimbursementActivity.this, "已加载全部数据！").show();
                    svReimbursement.onFinishFreshAndLoad();
                }
            }
        });
        adapter.setOnItemClickLitener(new ExpenseReimbursementAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

                String getExcelUrl = "";
                if (expenseReimbursement.getData().getList().get(position).getExamine() == 0) {
                    getExcelUrl = "";
                } else if (expenseReimbursement.getData().getList().get(position).getExamine() == 1 && expenseReimbursement.getData().getList().get(position).getPay() == 1) {
                    getExcelUrl = expenseReimbursement.getData().getList().get(position).getExpenseid()+"";
                } else if (expenseReimbursement.getData().getList().get(position).getExamine() == 1 && expenseReimbursement.getData().getList().get(position).getPay() == 0) {
                    getExcelUrl = expenseReimbursement.getData().getList().get(position).getExpenseid()+"";
                } else {
                    getExcelUrl = "";
                }
                CustomsWebViewActivity.actionStart(ExpenseReimbursementActivity.this, "费用报销", expenseReimbursement.getData().getList().get(position).getUrl(), getExcelUrl);
            }
        });


        layoutCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expenseReimbursement != null ) {
                    if (isHaveExcel()) {

                        if (!flag) {
                            flag = true;
                            tvRightTitle.setText("导出");
                            adapter.setFlag(true);
                            adapter.notifyDataSetChanged();
                        } else {
                            educeExcel = educeExcel();
                            if (educeExcel == null) {
                                Toast.makeText(ExpenseReimbursementActivity.this, "请选择导出项", Toast.LENGTH_SHORT).show();
                            } else {
                                dialog = new EmailTipDialog(ExpenseReimbursementActivity.this,email,R.style.MillionDialogStyle,onClickListener);
                                dialog.show();
                            }
                        }
                    }
                }else {
                    Toast.makeText(ExpenseReimbursementActivity.this, "没有可导出项", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String emailStr = dialog.getEmailStr();

            if (!Utils.isEmail(emailStr)){
                Toasty.error(ExpenseReimbursementActivity.this, "请填写正确邮箱").show();
                return;
            }
            if (TextUtils.isEmpty(emailStr)){
                Toasty.error(ExpenseReimbursementActivity.this, "请填写邮箱").show();
                return;
            }
            PreferenceUtils.getInstance().setEmail(emailStr);
            goExport(emailStr,educeExcel);

        }
    };


    private boolean isHaveExcel() {

        boolean mFlag = false;


        for (ExpenseReimbursement.DataBean.ListBean listBean : expenseReimbursement.getData().getList()) {
            if (listBean.getExamine() == 1 && listBean.getPay() == 1) {
                mFlag = true;
            } else if (listBean.getExamine() == 1 && listBean.getPay() == 0) {
                mFlag = true;
            }
        }

        if (!mFlag) {
            Toast.makeText(ExpenseReimbursementActivity.this, "没有可导出项", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //导出
    private String educeExcel() {

        boolean flag = false;
        List<String> idStr = adapter.getIdStr();
        idStrContent = "";
        for (String s : idStr) {
            if (flag) {
                idStrContent += "," + s;
            } else {
                flag = true;
                idStrContent += s;
            }
        }
        if (TextUtils.isEmpty(idStrContent)) {
            return null;
        } else {
            String excelUrl = BaseUrl.getInstence().getUrlByExcelUrl() + idStrContent;
            return excelUrl;
        }
    }

    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
//        manger.setQuestion("");
    }

    @Override
    protected void initDatas() {
        LoadDialog.show(this);
        loadData(Constant.REFRESH_STATUS, page);
    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        if (reimbursement != null && reimbursement.getData().getAdd_url() != null) {
            WebViewActivity.actionStart(ExpenseReimbursementActivity.this, "新增报销", reimbursement.getData().getAdd_url(), true);
        }
    }
    private void goExport(String email, String expenseid) {
        createRequest(BaseUrl.getInstence().getExpenceListUrl()).goExport(PreferenceUtils.getInstance().getUserToken(),expenseid,email).enqueue(new Callback<EmailEean>() {
            @Override
            public void onResponse(Call<EmailEean> call, Response<EmailEean> response) {
                dialog.dismiss();
                if (response.body().getCode() == 0){
                    Toasty.success(ExpenseReimbursementActivity.this, "导出成功，注意查收邮箱").show();
                }else {
                    Toasty.error(ExpenseReimbursementActivity.this, "导出失败").show();
                }
            }

            @Override
            public void onFailure(Call<EmailEean> call, Throwable t) {
                LoadDialog.dismiss(ExpenseReimbursementActivity.this);
                Toasty.error(ExpenseReimbursementActivity.this, getString(R.string.net_error) + ":获取费用报表失败！").show();
                Log.e("获取费用报销列表", t.toString());
                dialog.dismiss();
            }
        });
    }
    private void loadData(final int status, final int page) {
        createRequest(BaseUrl.getInstence().getExpenceListUrl()).examineGetExpenseList(PreferenceUtils.getInstance().getUserToken(), page).enqueue(new Callback<ExpenseReimbursement>() {
            @Override
            public void onResponse(Call<ExpenseReimbursement> call, Response<ExpenseReimbursement> response) {
                if (svReimbursement != null) {
                    svReimbursement.onFinishFreshAndLoad();
                }
                LoadDialog.dismiss(ExpenseReimbursementActivity.this);
                reimbursement = response.body();
                switch (reimbursement.getCode()) {
                    case Constant.SUCCESS:
                        if (reimbursement.getData() != null && reimbursement.getData().getList() != null && reimbursement.getData().getList().size() > 0) {
                            layoutDataNull.setVisibility(View.GONE);
                            if (status == Constant.REFRESH_STATUS) {
                                expenseReimbursement = reimbursement;
                                adapter.setDatas(expenseReimbursement);
                                RecyclerViewHelper.initRecyclerViewV(ExpenseReimbursementActivity.this, rvExpenseReimbursement, false, adapter);
                            } else {
                                expenseReimbursement.getData().setHas_next(reimbursement.getData().isHas_next());
                                expenseReimbursement.getData().getList().addAll(reimbursement.getData().getList());
                                adapter.setDatas(expenseReimbursement);
                            }
                        } else {
                            layoutDataNull.setVisibility(View.VISIBLE);
//                            Toasty.info(ExpenseReimbursementActivity.this, "暂无更多数据").show();
                        }
                        break;
                    default:
                        Toasty.error(ExpenseReimbursementActivity.this, reimbursement.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<ExpenseReimbursement> call, Throwable t) {
                LoadDialog.dismiss(ExpenseReimbursementActivity.this);
                if (svReimbursement != null) {
                    svReimbursement.onFinishFreshAndLoad();
                }
                Toasty.error(ExpenseReimbursementActivity.this, getString(R.string.net_error) + ":获取费用报销列表失败！").show();
                Log.e("获取费用报销列表", t.toString());
            }
        });
    }


}
