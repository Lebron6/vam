package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.DepartmentList;
import com.dy.vam.entity.Verif;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.widget.ButtomSelectWindow;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/1/31.
 * 添加新成员
 */

public class AddingNewStaffActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_depart)
    EditText etDepart;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.view_line)
    View viewLine;
    private ArrayAdapter arrayAdapter;
    private DepartmentList departmentList;
    private int departmentId;


    public static void actionStart(Context context, String title) {
        Intent intent = new Intent(context, AddingNewStaffActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivity(intent);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_add_new_staff;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {
        createRequest(BaseUrl.getInstence().getDepartmentListUrl()).getDepartmentList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<DepartmentList>() {
            @Override
            public void onResponse(Call<DepartmentList> call, Response<DepartmentList> response) {
                departmentList = response.body();
                switch (departmentList.getCode()) {
                    case Constant.SUCCESS:
                        if (departmentList.getData() != null) {
                            List<String> itemList = new ArrayList<String>();
                            for (int i = 0; i < departmentList.getData().size(); i++) {
                                String department = departmentList.getData().get(i).getName();
                                itemList.add(department);
                            }
                            arrayAdapter = new ArrayAdapter(AddingNewStaffActivity.this, R.layout.item_question, R.id.tv_popqusetion, itemList);
                        } else {
                            Toasty.error(AddingNewStaffActivity.this, "部门列表为空！").show();
                        }
                        break;
                    default:
                        Toasty.error(AddingNewStaffActivity.this, departmentList.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<DepartmentList> call, Throwable t) {
                Toasty.error(AddingNewStaffActivity.this, getString(R.string.net_error) + ":获取部门列表异常！").show();
            }
        });
    }

    @OnClick({R.id.image_show_departs, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_show_departs:
                if (arrayAdapter == null) {
                    Toasty.error(AddingNewStaffActivity.this, getString(R.string.net_error) + ":部门列表为空！").show();
                    return;
                }
                ButtomSelectWindow timeSelectWindow = new ButtomSelectWindow(AddingNewStaffActivity.this);
                timeSelectWindow.showView( viewLine,arrayAdapter, new OnTimeSelectedListener() {
                    @Override
                    public void select(int postion) {
                        etDepart.setText(departmentList.getData().get(postion).getName());
                        departmentId = departmentList.getData().get(postion).getDepartmentid();
                    }
                });
                break;
            case R.id.btn_add:
                String name = etName.getText().toString();
                String phone = etPhoneNum.getText().toString();
                String post = etPosition.getText().toString();
                String departName = etDepart.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toasty.error(AddingNewStaffActivity.this, "请输入员工名称").show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toasty.error(AddingNewStaffActivity.this, "请输入员手机号码").show();
                    return;
                }
//                if (!Utils.isMobileNO(phone)){
//                    Toasty.error(AddingNewStaffActivity.this, "手机号码格式有误").show();
//                    return;
//                }
                if (TextUtils.isEmpty(post)) {
                    Toasty.error(AddingNewStaffActivity.this, "请输入员工职位 ").show();
                    return;
                }
                if (TextUtils.isEmpty(departName)) {
                    Toasty.error(AddingNewStaffActivity.this, "请选择部门 ").show();
                    return;
                }
                toAddNewStaff(name, phone, post, departName);
                break;
        }
    }

    private void toAddNewStaff(String name, String phone, String post, String departName) {
        btnAdd.setEnabled(false);
        LoadDialog.show(AddingNewStaffActivity.this);
        createRequest(BaseUrl.getInstence().getAddNewStaffUrl()).addNewStaff(PreferenceUtils.getInstance().getUserToken(), name, phone, departmentId, post).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(AddingNewStaffActivity.this);
                btnAdd.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(AddingNewStaffActivity.this, "成员添加成功").show();
                        finish();
                        break;
                    default:
                        Toasty.error(AddingNewStaffActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnAdd.setEnabled(true);
                LoadDialog.dismiss(AddingNewStaffActivity.this);
                Toasty.warning(AddingNewStaffActivity.this, getString(R.string.net_error)).show();
                Log.e("成员添加失败：", t.toString());
            }
        });
    }

}
