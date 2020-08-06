package com.dy.vam.ui.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.ExaminegetNewTask;
import com.dy.vam.entity.TaskUpdataData;
import com.dy.vam.entity.Verif;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TimeSelectWindow;
import com.dy.vam.ui.widget.TitleBarManger;
import com.dy.vam.ui.widget.YearMonthDatePickerDialog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by James on 2018/1/26.
 * 新增任务待审批
 */

public class NewTaskToBeEApprovedActivity extends BaseActivity {


    public static final String DETAILS_VALUES = "title";
    public static final String TASK_ID = "taskid";
    @BindView(R.id.et_task_name)
    EditText etTaskName;
    @BindView(R.id.tv_final_time)
    TextView tvFinalTime;
    @BindView(R.id.tv_project_type)
    TextView tvProjectType;
    @BindView(R.id.tv_project_time)
    TextView tvProjectTime;
    @BindView(R.id.tv_project_urgent)
    TextView tvProjectUrgent;
    @BindView(R.id.tv_project_importent)
    TextView tvProjectImportent;
    @BindView(R.id.tv_staff_load)
    TextView tvStaffLoad;
    @BindView(R.id.et_integral)
    EditText etIntegral;
    @BindView(R.id.btn_ea_succ)
    Button btnEaSucc;
    @BindView(R.id.btn_updata)
    Button btnUpdata;
    @BindView(R.id.image_one)
    ImageView imageOne;
    @BindView(R.id.image_two)
    ImageView imageTwo;
    @BindView(R.id.image_three)
    ImageView imageThree;
    @BindView(R.id.image_four)
    ImageView imageFour;
    @BindView(R.id.image_five)
    ImageView imageFive;
    @BindView(R.id.image_six)
    ImageView imageSix;
    @BindView(R.id.image_seven)
    ImageView imageSeven;
    @BindView(R.id.image_eight)
    ImageView imageEight;
    private boolean isUpdataStatus; //是否是更改状态

    private List<String> projectTypes;
    private List<String> projectTimes;
    private List<String> projectUrgents;
    private List<String> projectImportents;
    private List<String> staffLoads;
    private ArrayAdapter arrayAdapter;

    private int projectTypeTypeId;
    private int projectTimeTypeId;
    private int projectUrgentTypeId;
    private int projectImportentTypeId;
    private int staffLoadsTypeId;
    private String finishTimeStamp;
    private TaskUpdataData taskUpdataData;
    private TitleBarManger manger;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title, int taskid) {
        Intent intent = new Intent(context, NewTaskToBeEApprovedActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(TASK_ID, taskid);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
        loadDatas();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_task_examined_approved;
    }

    @Override
    protected void initViews() {
    }

    public void loadDatas() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getExamineGetNewTaskUrl()).examineGetNewTask(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<ExaminegetNewTask>() {
            @Override
            public void onResponse(Call<ExaminegetNewTask> call, Response<ExaminegetNewTask> response) {
                LoadDialog.dismiss(NewTaskToBeEApprovedActivity.this);
                if (response.body().getCode() == Constant.SUCCESS) {
                    if (response.body().getData() != null) {
                        manger.setTitle(response.body().getData().getTitle());
                        etTaskName.setText(response.body().getData().getName());
                        tvFinalTime.setText(response.body().getData().getFinishtime());
                        etIntegral.setText(response.body().getData().getIntegration());
                        tvProjectImportent.setText(response.body().getData().getStandard4().getName());
                        tvProjectTime.setText(response.body().getData().getStandard2().getName());
                        tvProjectType.setText(response.body().getData().getStandard1().getName());
                        tvProjectUrgent.setText(response.body().getData().getStandard3().getName());
                        tvStaffLoad.setText(response.body().getData().getStandard5().getName());
                        //初始化新增任务原始TypeId原始数据
                        projectTimeTypeId = response.body().getData().getStandard2().getTypeid();
                        projectImportentTypeId = response.body().getData().getStandard4().getTypeid();
                        projectTypeTypeId = response.body().getData().getStandard1().getTypeid();
                        projectUrgentTypeId = response.body().getData().getStandard3().getTypeid();
                        staffLoadsTypeId = response.body().getData().getStandard5().getTypeid();
                        try {
                            finishTimeStamp = Utils.dateToStamp(response.body().getData().getFinishtime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toasty.error(NewTaskToBeEApprovedActivity.this, "获取新增任务数据失败！").show();
                    }
                } else {
                    Toasty.error(NewTaskToBeEApprovedActivity.this, response.body().getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<ExaminegetNewTask> call, Throwable t) {
                LoadDialog.dismiss(NewTaskToBeEApprovedActivity.this);
                Toasty.error(NewTaskToBeEApprovedActivity.this, getString(R.string.net_error) + ":获取新增任务书记失败！").show();
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_ea_succ, R.id.btn_updata, R.id.tv_final_time, R.id.tv_project_type, R.id.tv_project_time, R.id.tv_project_urgent, R.id.tv_project_importent, R.id.tv_staff_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ea_succ:
                btnEaSucc.setEnabled(false);
                LoadDialog.show(NewTaskToBeEApprovedActivity.this);
                createRequest(BaseUrl.getInstence().getExaminePassNewTaskUrl()).examinePassNewtask(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<Verif>() {
                    @Override
                    public void onResponse(Call<Verif> call, Response<Verif> response) {
                        LoadDialog.dismiss(NewTaskToBeEApprovedActivity.this);
                        btnEaSucc.setEnabled(true);
                        switch (response.body().getCode()) {
                            case Constant.SUCCESS:
                                Toasty.info(NewTaskToBeEApprovedActivity.this, "审批成功").show();
                                finish();
                                break;
                            default:
                                Toasty.error(NewTaskToBeEApprovedActivity.this, response.body().getMsg()).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Verif> call, Throwable t) {
                        btnEaSucc.setEnabled(true);
                        LoadDialog.dismiss(NewTaskToBeEApprovedActivity.this);
                        Toasty.warning(NewTaskToBeEApprovedActivity.this, getString(R.string.net_error)).show();
                    }
                });
                break;
            case R.id.btn_updata:
                if (!isUpdataStatus) {
                    toUpdataStatus();
                } else {
                    if (TextUtils.isEmpty(etTaskName.getText().toString())) {
                        Toasty.warning(NewTaskToBeEApprovedActivity.this, "请输入新增任务名").show();
                        return;
                    }else{
                        unUpdataStatus();
                    }
                }
                break;
            case R.id.tv_final_time:
                if (isUpdataStatus) {
                    YearMonthDatePickerDialog datePickerDialog = new YearMonthDatePickerDialog(NewTaskToBeEApprovedActivity.this, R.style.MyDatePickerDialogTheme, onFinishSelectedListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
                break;
            case R.id.tv_project_type:
                if (isUpdataStatus) {
                    if (projectTypes != null && projectTypes.size() > 0) {
                        arrayAdapter = new ArrayAdapter(NewTaskToBeEApprovedActivity.this, R.layout.item_question, R.id.tv_popqusetion, projectTypes);
                        TimeSelectWindow timeSelectWindow = new TimeSelectWindow(NewTaskToBeEApprovedActivity.this);
                        timeSelectWindow.showView(findViewById(R.id.tv_project_type), arrayAdapter, onProjectTypeSelectedListener);
                    } else {
                        Toasty.error(NewTaskToBeEApprovedActivity.this, "获取项目类别列表失败").show();
                    }
                }
                break;
            case R.id.tv_project_time:
                if (isUpdataStatus) {
                    if (projectTimes != null && projectTimes.size() > 0) {
                        arrayAdapter = new ArrayAdapter(NewTaskToBeEApprovedActivity.this, R.layout.item_question, R.id.tv_popqusetion, projectTimes);
                        TimeSelectWindow timeSelectWindow = new TimeSelectWindow(NewTaskToBeEApprovedActivity.this);
                        timeSelectWindow.showView(findViewById(R.id.tv_project_time), arrayAdapter, onProjectTimeSelectedListener);
                    } else {
                        Toasty.error(NewTaskToBeEApprovedActivity.this, "获取项目工时列表失败").show();
                    }
                }
                break;
            case R.id.tv_project_urgent:
                if (isUpdataStatus) {
                    if (projectUrgents != null && projectUrgents.size() > 0) {
                        arrayAdapter = new ArrayAdapter(NewTaskToBeEApprovedActivity.this, R.layout.item_question, R.id.tv_popqusetion, projectUrgents);
                        TimeSelectWindow timeSelectWindow = new TimeSelectWindow(NewTaskToBeEApprovedActivity.this);
                        timeSelectWindow.showView(findViewById(R.id.tv_project_urgent), arrayAdapter, onProjectUrgentSelectedListener);
                    } else {
                        Toasty.error(NewTaskToBeEApprovedActivity.this, "获取项目紧急度列表失败").show();
                    }
                }
                break;
            case R.id.tv_project_importent:
                if (isUpdataStatus) {
                    if (projectImportents != null && projectImportents.size() > 0) {
                        arrayAdapter = new ArrayAdapter(NewTaskToBeEApprovedActivity.this, R.layout.item_question, R.id.tv_popqusetion, projectImportents);
                        TimeSelectWindow timeSelectWindow = new TimeSelectWindow(NewTaskToBeEApprovedActivity.this);
                        timeSelectWindow.showView(findViewById(R.id.tv_project_importent), arrayAdapter, onProjecImportentSelectedListener);
                    } else {
                        Toasty.error(NewTaskToBeEApprovedActivity.this, "获取项目重要性列表失败").show();
                    }
                }
                break;
            case R.id.tv_staff_load:
                if (isUpdataStatus) {
                    if (staffLoads != null && staffLoads.size() > 0) {
                        arrayAdapter = new ArrayAdapter(NewTaskToBeEApprovedActivity.this, R.layout.item_question, R.id.tv_popqusetion, staffLoads);
                        TimeSelectWindow timeSelectWindow = new TimeSelectWindow(NewTaskToBeEApprovedActivity.this);
                        timeSelectWindow.showView(findViewById(R.id.tv_staff_load), arrayAdapter, onStaffLoadSelectedListener);
                    } else {
                        Toasty.error(NewTaskToBeEApprovedActivity.this, "获取员工负荷列表失败").show();
                    }
                }
                break;
        }
    }

    private void unUpdataStatus() {
       LoadDialog.show(this);
        btnUpdata.setEnabled(false);
        createRequest(BaseUrl.getInstence().getExamineEditNewTaskUrl()).updataTaskData(PreferenceUtils.getInstance().getUserToken(),
                getIntent().getIntExtra(TASK_ID, 0),
                etTaskName.getText().toString(), finishTimeStamp,
                projectTypeTypeId,projectTimeTypeId,projectUrgentTypeId,
                projectImportentTypeId,staffLoadsTypeId).enqueue(new Callback<Verif>() {
            @Override
            public void onResponse(Call<Verif> call, Response<Verif> response) {
                LoadDialog.dismiss(NewTaskToBeEApprovedActivity.this);
                btnUpdata.setEnabled(true);
                switch (response.body().getCode()) {
                    case Constant.SUCCESS:
                        isUpdataStatus = false;
                        etIntegral.setEnabled(false);
                        etTaskName.setEnabled(false);
                        imageTwo.setVisibility(View.INVISIBLE);
                        imageThree.setVisibility(View.INVISIBLE);
                        imageFour.setVisibility(View.INVISIBLE);
                        imageFive.setVisibility(View.INVISIBLE);
                        imageSix.setVisibility(View.INVISIBLE);
                        imageEight.setVisibility(View.INVISIBLE);
                        btnEaSucc.setVisibility(View.VISIBLE);
                        btnUpdata.setText("更改");
                        Toasty.info(NewTaskToBeEApprovedActivity.this, "更改成功").show();
                        loadDatas();
                        break;
                    default:
                        Toasty.error(NewTaskToBeEApprovedActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Verif> call, Throwable t) {
                btnUpdata.setEnabled(true);
                LoadDialog.dismiss(NewTaskToBeEApprovedActivity.this);
                Toasty.warning(NewTaskToBeEApprovedActivity.this, getString(R.string.net_error)).show();
                Log.e("更改失败：", t.toString());
            }
        });
    }

    /**
     * 更改预计完成时间
     */
    DatePickerDialog.OnDateSetListener onFinishSelectedListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            tvFinalTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            try {
                finishTimeStamp = Utils.dateToStamp(year + "-" + (month + 1) + "-" + dayOfMonth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 项目类别
     */
    OnTimeSelectedListener onProjectTypeSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            tvProjectType.setText(projectTypes.get(postion));
            projectTypeTypeId = taskUpdataData.getData().getStandard1().get(postion).getTypeid();
        }
    };

    /**
     * 项目紧急度
     */
    OnTimeSelectedListener onProjectUrgentSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            tvProjectUrgent.setText(projectUrgents.get(postion));
            projectUrgentTypeId = taskUpdataData.getData().getStandard3().get(postion).getTypeid();
        }
    };

    /**
     * 项目工时
     */
    OnTimeSelectedListener onProjectTimeSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            tvProjectTime.setText(projectTimes.get(postion));
            projectTimeTypeId = taskUpdataData.getData().getStandard2().get(postion).getTypeid();
        }
    };

    /**
     * 项目重要性
     */
    OnTimeSelectedListener onProjecImportentSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            tvProjectImportent.setText(projectImportents.get(postion));
            projectImportentTypeId = taskUpdataData.getData().getStandard4().get(postion).getTypeid();
        }
    };

    /**
     * 员工负荷
     */
    OnTimeSelectedListener onStaffLoadSelectedListener = new OnTimeSelectedListener() {
        @Override
        public void select(int postion) {
            tvStaffLoad.setText(staffLoads.get(postion));
            staffLoadsTypeId = taskUpdataData.getData().getStandard5().get(postion).getTypeid();
        }
    };

    private void toUpdataStatus() {
        getAllUpdataData();
    }

    private void getAllUpdataData() {
        createRequest(BaseUrl.getInstence().getExamineGetStandardDataUrl()).getUpdataTaskData(PreferenceUtils.getInstance().getUserToken(), getIntent().getIntExtra(TASK_ID, 0)).enqueue(new Callback<TaskUpdataData>() {
            @Override
            public void onResponse(Call<TaskUpdataData> call, Response<TaskUpdataData> response) {
                taskUpdataData = response.body();
                if (taskUpdataData.getCode() == Constant.SUCCESS) {
                    if (taskUpdataData.getData() != null) {
                        //获取更改起初数据成功后 更新界面
                        etTaskName.setEnabled(true);
                        imageTwo.setVisibility(View.VISIBLE);
                        imageThree.setVisibility(View.VISIBLE);
                        imageFour.setVisibility(View.VISIBLE);
                        imageFive.setVisibility(View.VISIBLE);
                        imageSix.setVisibility(View.VISIBLE);
                        imageEight.setVisibility(View.VISIBLE);
                        isUpdataStatus = true;
                        btnEaSucc.setVisibility(View.INVISIBLE);
                        btnUpdata.setText("完成");

                        if (taskUpdataData.getData().getStandard1() != null && taskUpdataData.getData().getStandard1().size() > 0) {
                            projectTypes = new ArrayList<String>();
                            for (int i = 0; i < taskUpdataData.getData().getStandard1().size(); i++) {
                                projectTypes.add(taskUpdataData.getData().getStandard1().get(i).getName());
                            }
                        }
                        if (taskUpdataData.getData().getStandard2() != null && taskUpdataData.getData().getStandard2().size() > 0) {
                            projectTimes = new ArrayList<String>();
                            for (int i = 0; i < taskUpdataData.getData().getStandard2().size(); i++) {
                                projectTimes.add(taskUpdataData.getData().getStandard2().get(i).getName());
                            }
                        }
                        if (taskUpdataData.getData().getStandard3() != null && taskUpdataData.getData().getStandard3().size() > 0) {
                            projectUrgents = new ArrayList<String>();
                            for (int i = 0; i < taskUpdataData.getData().getStandard3().size(); i++) {
                                projectUrgents.add(taskUpdataData.getData().getStandard3().get(i).getName());
                            }
                        }
                        if (taskUpdataData.getData().getStandard4() != null && taskUpdataData.getData().getStandard4().size() > 0) {
                            projectImportents = new ArrayList<String>();
                            for (int i = 0; i < taskUpdataData.getData().getStandard4().size(); i++) {
                                projectImportents.add(taskUpdataData.getData().getStandard4().get(i).getName());
                            }
                        }
                        if (taskUpdataData.getData().getStandard5() != null && taskUpdataData.getData().getStandard5().size() > 0) {
                            staffLoads = new ArrayList<String>();
                            for (int i = 0; i < taskUpdataData.getData().getStandard5().size(); i++) {
                                staffLoads.add(taskUpdataData.getData().getStandard5().get(i).getName());
                            }
                        }
                    }
                } else {
                    Toasty.error(NewTaskToBeEApprovedActivity.this, taskUpdataData.getMsg()).show();
                }
            }

            @Override
            public void onFailure(Call<TaskUpdataData> call, Throwable t) {
                Toasty.error(NewTaskToBeEApprovedActivity.this, "获取新任务基础数据失败").show();
            }
        });
    }


}
