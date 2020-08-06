package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.DepartStaffDetails;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.GlideCircleTransform;
import com.dy.vam.tools.PreferenceUtils;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/31.
 * 员工详情/部门首页成员管理
 */

public class DepartmentGetEmployeeDetailsActivity extends BaseActivity {
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

    public static final String DETAILS_VALUES = "id";
    @BindView(R.id.tv_star_num)
    TextView tvStarNum;
    @BindView(R.id.btn_edit)
    Button btnEdit;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false).statusBarView(R.id.view_status_bar)
                .statusBarColor(R.color.colorTheme).init();
    }
    public static void actionStart(Context context, int id) {
        Intent intent = new Intent(context, DepartmentGetEmployeeDetailsActivity.class);
        intent.putExtra(DETAILS_VALUES, id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_depart_staff_details;
    }

    @Override
    protected void initViews() {
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
    }

    @Override
    protected void initDatas() {
        createRequest(BaseUrl.getInstence().getEmployeeUrl()).getUserInfoById(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(DETAILS_VALUES, 1),PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<DepartStaffDetails>() {
            @Override
            public void onResponse(Call<DepartStaffDetails> call, Response<DepartStaffDetails> response) {
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Glide.with(DepartmentGetEmployeeDetailsActivity.this).load(response.body().getData().getHeadimg()).bitmapTransform(new GlideCircleTransform(DepartmentGetEmployeeDetailsActivity.this)).into(imageIcon);
                        tvCompany.setText(response.body().getData().getCompany() + "");
                        tvCommissionMoney.setText(response.body().getData().getCommission() + "");
                        tvDepartmentName.setText(response.body().getData().getDepartment() + "");
                        tvName.setText(response.body().getData().getUsername() + "");
                        tvPhoneNum.setText(response.body().getData().getPhone() + "");
                        tvPosition.setText(response.body().getData().getPost() + "");
                        tvPriceMoney.setText(response.body().getData().getValue() + "");
//                        tvStarNum.setText(response.body().getData().getStar() + "");
                        break;
                    default:
                        Toasty.error(DepartmentGetEmployeeDetailsActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<DepartStaffDetails> call, Throwable t) {
                Toasty.error(DepartmentGetEmployeeDetailsActivity.this, getString(R.string.net_error) + ":获取员工详情失败！").show();
                Log.e("获取员工详情", t.toString());
            }
        });
    }


    @OnClick({R.id.layout_back, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_delete:
                Toasty.info(DepartmentGetEmployeeDetailsActivity.this, "删除该员工").show();
                break;
        }
    }
}
