package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.DepartmentList;
import com.dy.vam.entity.Verif;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.widget.ButtomSelectWindow;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/3/12.
 * 编辑员工信息
 */

public class EditStaffInfoActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String USER_ID = "userId";
    public static final String DEPARTMENT_ID = "departmentId";
    public static final String DEPARTMENT = "department";
    public static final String PHONE_NUM = "phone_num";
    public static final String POSITION = "position";
    public static final String IS_MANGER = "isManger";
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.btn_updata)
    Button btnUpdata;
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_question)
    LinearLayout layoutQuestion;
    @BindView(R.id.layout_notice)
    LinearLayout layoutNotice;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.et_depart)
    EditText etDepart;
    @BindView(R.id.image_show_departs)
    ImageView imageShowDeparts;
    @BindView(R.id.cb_set_post)
    CheckBox cbSetPost;
    @BindView(R.id.view_line)
    View viewLine;
    private int isManger;
    private ArrayAdapter arrayAdapter;
    private DepartmentList departmentList;
    private int departmentId;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title, int departmentId, String departent, int userId, String phoneNum, String position, int isManger) {
        Intent intent = new Intent(context, EditStaffInfoActivity.class);
        intent.putExtra(USER_ID, userId);
        intent.putExtra(DEPARTMENT_ID, departmentId);
        intent.putExtra(DEPARTMENT, departent);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(PHONE_NUM, phoneNum);
        intent.putExtra(POSITION, position);
        intent.putExtra(IS_MANGER, isManger);
        context.startActivity(intent);
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
        return R.layout.activity_edit_staff;
    }

    @Override
    protected void initViews() {
        etPhoneNum.setText(getIntent().getStringExtra(PHONE_NUM));
        etPosition.setText(getIntent().getStringExtra(POSITION));
        etDepart.setText(getIntent().getStringExtra(DEPARTMENT));
        cbSetPost.setOnCheckedChangeListener(onCheckedChangeListener);
        cbSetPost.setChecked(getIntent().getIntExtra(IS_MANGER, 0) == 1);
        departmentId = getIntent().getIntExtra(DEPARTMENT_ID, 0);
        isManger = getIntent().getIntExtra(IS_MANGER, 0);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                isManger = 1;
            } else {
                isManger = 0;
            }
        }
    };

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
                            arrayAdapter = new ArrayAdapter(EditStaffInfoActivity.this, R.layout.item_question, R.id.tv_popqusetion, itemList);
                        } else {
                            Toasty.error(EditStaffInfoActivity.this, "部门列表为空！").show();
                        }
                        break;
                    default:
                        Toasty.error(EditStaffInfoActivity.this, departmentList.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<DepartmentList> call, Throwable t) {
                Toasty.error(EditStaffInfoActivity.this, getString(R.string.net_error) + ":获取部门列表异常！").show();
            }
        });
    }


    @OnClick(R.id.btn_updata)
    public void onViewClicked() {
        String phoneNum = etPhoneNum.getText().toString();
        String position = etPosition.getText().toString();
        if (TextUtils.isEmpty(phoneNum)) {
            Toasty.error(EditStaffInfoActivity.this, "请输入该人员手机号码").show();
            return;
        }
        if (TextUtils.isEmpty(position)) {
            Toasty.error(EditStaffInfoActivity.this, "请输入该人员职位").show();
            return;
        }
        updata(phoneNum, position, getIntent().getIntExtra(USER_ID, 1), departmentId);
    }

    private void updata(String phoneNum, String position, int userId, int departmentId) {
//        Log.e("编辑修改人员信息","手机号:"+phoneNum+"职位:"+position+"部门id:"+departmentId+"人员Id"+userId);
        btnUpdata.setEnabled(false);
        LoadDialog.show(this);
        Log.e("777", isManger + "");
        createRequest(BaseUrl.getInstence().getAdminEditUserUrl()).adminEditUser(PreferenceUtils.getInstance().getUserToken(), userId, phoneNum, departmentId, position, isManger).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(EditStaffInfoActivity.this);
                btnUpdata.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        Toasty.info(EditStaffInfoActivity.this, "员工信息修改成功！").show();
                        finish();
                        break;
                    default:
                        Toasty.error(EditStaffInfoActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnUpdata.setEnabled(true);
                LoadDialog.dismiss(EditStaffInfoActivity.this);
                Toasty.warning(EditStaffInfoActivity.this, getString(R.string.net_error)).show();
                Log.e("员工信息修改失败：", t.toString());
            }
        });

    }

    @OnClick({R.id.image_show_departs, R.id.btn_updata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_show_departs:
                if (arrayAdapter == null) {
                    Toasty.error(EditStaffInfoActivity.this, getString(R.string.net_error) + ":部门列表为空！").show();
                    return;
                }
                ButtomSelectWindow timeSelectWindow = new ButtomSelectWindow(EditStaffInfoActivity.this);
                timeSelectWindow.showView(viewLine, arrayAdapter, new OnTimeSelectedListener() {
                    @Override
                    public void select(int postion) {
                        etDepart.setText(departmentList.getData().get(postion).getName());
                        departmentId = departmentList.getData().get(postion).getDepartmentid();
                    }
                });

                break;
            case R.id.btn_updata:
                break;
        }
    }
}
