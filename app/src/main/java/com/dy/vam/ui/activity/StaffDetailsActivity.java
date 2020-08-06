package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.DepartStaffDetails;
import com.dy.vam.entity.Verif;
import com.dy.vam.tools.GlideCircleTransform;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TipsDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.OperaConstant.MANAGE_ORGANIZATION;

/**
 * Created by James on 2018/1/31.
 * 员工详情
 */

public class StaffDetailsActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.image_icon)
    ImageView imageIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.tv_department_name)
    TextView tvDepartmentName;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_price_money)
    TextView tvPriceMoney;
    @BindView(R.id.tv_commission_money)
    TextView tvCommissionMoney;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_edit)
    Button btnEdit;
    @BindView(R.id.tv_star_num)
    TextView tvStarNum;
    @BindView(R.id.cb_set_post)
    CheckBox cbSetPost;
    @BindView(R.id.layout_set_post)
    RelativeLayout layoutSetPost;
    @BindView(R.id.tv_is_manger)
    TextView tvIsManger;
    private String type;

    public static final String USER_ID = "id";
    public static final String TYPE = "type";
    private DepartStaffDetails staffDetails;
    private TipsDialog deleteStaffDialog;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
                .statusBarColor(R.color.colorTheme).init();
    }

    public static void actionStart(Context context, int id, String type) {
        Intent intent = new Intent(context, StaffDetailsActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(USER_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        LoadDialog.show(StaffDetailsActivity.this);
        createRequest(BaseUrl.getInstence().getUserInfoUrl()).getUserInfoById(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(USER_ID, 1),PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<DepartStaffDetails>() {
            @Override
            public void onResponse(Call<DepartStaffDetails> call, Response<DepartStaffDetails> response) {
                LoadDialog.dismiss(StaffDetailsActivity.this);
                staffDetails = response.body();
                switch (staffDetails.getCode()) {
                    case Constant.SUCCESS:
                        if (staffDetails.getData() != null) {
                            Glide.with(StaffDetailsActivity.this).load(staffDetails.getData().getHeadimg()).bitmapTransform(new GlideCircleTransform(StaffDetailsActivity.this)).into(imageIcon);
                            tvCompany.setText(staffDetails.getData().getCompany() + "");
                            tvCommissionMoney.setText(staffDetails.getData().getCommission() + "");
                            tvDepartmentName.setText(staffDetails.getData().getDepartment() + "");
                            tvName.setText(staffDetails.getData().getUsername() + "");
                            tvPhoneNum.setText(staffDetails.getData().getPhone() + "");
                            tvPosition.setText(staffDetails.getData().getPost() + "");
                            tvPriceMoney.setText(staffDetails.getData().getAssess_money() + "");
                            cbSetPost.setChecked(staffDetails.getData().getIsdirector() == 1);
                            tvIsManger.setText(staffDetails.getData().getIsdirector() == 1?"是":"否");
                            layoutSetPost.setVisibility(staffDetails.getData().getIsdirector() == 1?View.VISIBLE:View.GONE);
                        } else {
                            Toasty.error(StaffDetailsActivity.this, "获取员工信息失败").show();
                        }
                        break;
                    default:
                        Toasty.error(StaffDetailsActivity.this, staffDetails.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<DepartStaffDetails> call, Throwable t) {
                LoadDialog.dismiss(StaffDetailsActivity.this);
                Toasty.error(StaffDetailsActivity.this, getString(R.string.net_error) + ":获取员工详情失败！").show();
                Log.e("获取员工详情", t.toString());
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_staff_details;
    }

    @Override
    protected void initViews() {

        type = getIntent().getStringExtra(TYPE);
        if (type.equals(MANAGE_ORGANIZATION)) {
            btnDelete.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
//            layoutSetPost.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
//            layoutSetPost.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.btn_edit, R.id.layout_back, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_edit:
                EditStaffInfoActivity.actionStart(StaffDetailsActivity.this, "编辑人员信息", staffDetails.getData().getDepartmentid(),staffDetails.getData().getDepartment(), getIntent().getIntExtra(USER_ID, 1), staffDetails.getData().getPhone(), staffDetails.getData().getPost(),staffDetails.getData().getIsdirector());
                break;
            case R.id.btn_delete:
                deleteUser();
                break;
        }
    }

    private void deleteUser() {
        deleteStaffDialog = new TipsDialog(StaffDetailsActivity.this, "确定删除该员工吗?", new TipsDialog.DialogClickListener() {
            @Override
            public void yes() {
                btnDelete.setEnabled(false);
                LoadDialog.show(StaffDetailsActivity.this);
                createRequest(BaseUrl.getInstence().getAdminDeleteUserUrl()).adminDeleteUser(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(USER_ID, 1)).enqueue(new Callback<Verif>() {
                    @Override
                    public void onResponse(Call<Verif> call, Response<Verif> response) {
                        LoadDialog.dismiss(StaffDetailsActivity.this);
                        if (deleteStaffDialog!=null){
                            deleteStaffDialog.dismiss();
                        }
                        deleteStaffDialog.dismiss();
                        btnDelete.setEnabled(true);
                        Log.e("删除员工：", response.body().toString());
                        switch (response.body().getCode()) {
                            case Constant.SUCCESS:
                                Toasty.info(StaffDetailsActivity.this, "员工删除成功").show();
                                finish();
                                break;
                            default:
                                Toasty.error(StaffDetailsActivity.this, response.body().getMsg()).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Verif> call, Throwable t) {
                        btnDelete.setEnabled(true);
                        if (deleteStaffDialog!=null){
                            deleteStaffDialog.dismiss();
                        }
                        LoadDialog.dismiss(StaffDetailsActivity.this);
                    }
                });
            }

            @Override
            public void no() {
                btnDelete.setEnabled(true);
                if (deleteStaffDialog!=null){
                    deleteStaffDialog.dismiss();
                }
                LoadDialog.dismiss(StaffDetailsActivity.this);
            }
        });
        deleteStaffDialog.show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
