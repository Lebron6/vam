package com.dy.vam.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.callback.OnStaffCheckedListener;
import com.dy.vam.config.Constant;
import com.dy.vam.entity.Organization;
import com.dy.vam.entity.SortModelInfo;
import com.dy.vam.tools.CharacterParser;
import com.dy.vam.tools.PinyinComparator;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.StaffRadioAdapter;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/2/7.
 * 选择一级主管或员工界面/单选
 */

public class SelectSupervisorOrStaffActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";
    public static final String CHECKED_USER_ID = "checked_user_id";
    public static final String TYPE = "type";

    @BindView(R.id.lv_staff)
    ListView lvStaff;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.cb_checked_supervisor)
    CheckBox cbCheckedSupervisor;
    @BindView(R.id.layout_supervisor)
    RelativeLayout layoutSupervisor;
    @BindView(R.id.actv_search)
    AutoCompleteTextView actvSearch;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private List<SortModelInfo> sortModelInfos;
    private StaffRadioAdapter adapter;
    private int checkedUserId;  //H5调用选择人员时，默认有选中的人
    ArrayAdapter<String> arrayAdapter ;
    private String[] names;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStartForResult(Activity context, String title, int userId, int type) {
        Intent intent = new Intent(context, SelectSupervisorOrStaffActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(CHECKED_USER_ID, userId);
        intent.putExtra(TYPE, type);
        context.startActivityForResult(intent, Constant.REQUEST_CODE);
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
        return R.layout.activity_supervisor_or_staff_select;
    }

    @Override
    protected void initViews() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        checkedUserId = getIntent().getIntExtra(CHECKED_USER_ID, -1);
        cbCheckedSupervisor.setChecked(checkedUserId == 0 ? true : false);
        layoutSupervisor.setVisibility(getIntent().getIntExtra(TYPE, -1) == 0 ? View.GONE : View.VISIBLE);
        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=actvSearch.getText().toString();
                Log.e("获取到的人名",names[position]);
                if (!TextUtils.isEmpty(name)){
                    List<SortModelInfo> list=new ArrayList<>();
                    for (int i = 0; i <sortModelInfos.size() ; i++) {
                        if (sortModelInfos.get(i).getName().equals(name)){
                            list.add(sortModelInfos.get(i));
                        }
                    }
                    adapter.updateListView(list);
                }
            }
        });
    }

    @Override
    protected void initDatas() {
        loadDatas();
    }

    private void loadDatas() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getOrganizationUrl()).getOrganization(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<Organization>() {
            @Override
            public void onResponse(Call<Organization> call, Response<Organization> response) {
                LoadDialog.dismiss(SelectSupervisorOrStaffActivity.this);
                Organization organization = response.body();
                switch (organization.getCode()) {
                    case 0:
                        //初始化部门列表数据
                        if (organization.getData() != null) {
                            //根据部门成员名字排序
                            sortModelInfos = filledData(organization);
                            Collections.sort(sortModelInfos, pinyinComparator);
                            adapter = new StaffRadioAdapter(SelectSupervisorOrStaffActivity.this, sortModelInfos, onStaffCheckedListener);
                            lvStaff.setAdapter(adapter);
                            Utils.setListView(lvStaff);
                            lvStaff.setFocusable(false);
                            cbCheckedSupervisor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked) {
                                        for (int i = 0; i < sortModelInfos.size(); i++) {
                                            sortModelInfos.get(i).type = sortModelInfos.get(i).TYPE_NOCHECKED;
                                        }
                                        if (adapter != null) {
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            });

                          arrayAdapter = new ArrayAdapter<String>(SelectSupervisorOrStaffActivity.this,
                                    R.layout.item_auto_text, getAllStaffNameList(sortModelInfos));
                          actvSearch.setAdapter(arrayAdapter);
//                            actvSearch.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        } else {
                            Toasty.error(SelectSupervisorOrStaffActivity.this, "获取组织架构数据失败").show();
                        }

                        break;
                    default:
                        Toasty.error(SelectSupervisorOrStaffActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Organization> call, Throwable t) {
                LoadDialog.dismiss(SelectSupervisorOrStaffActivity.this);
                Toasty.error(SelectSupervisorOrStaffActivity.this, getString(R.string.net_error) + ":获取组织架构数据失败！").show();
            }
        });

    }

    private String[] getAllStaffNameList(List<SortModelInfo> sortModelInfos){
        names = new String[sortModelInfos.size()];
        for (int i = 0; i < sortModelInfos.size(); i++) {
            names[i]=sortModelInfos.get(i).getName();
        }
        return names;
    }
    OnStaffCheckedListener onStaffCheckedListener = new OnStaffCheckedListener() {
        @Override
        public void onCheckedCheckenListener(int position) {
            cbCheckedSupervisor.setChecked(false);
            for (int i = 0; i < sortModelInfos.size(); i++) {
                sortModelInfos.get(i).type = sortModelInfos.get(i).TYPE_NOCHECKED;
            }
        }
    };



    private List<SortModelInfo> filledData(Organization body) {

        List<SortModelInfo> mSortList = new ArrayList<>();
        for (int i = 0; i < body.getData().getUser().size(); i++) {
            for (int j = 0; j < body.getData().getUser().get(i).getList().size(); j++) {
                if (TextUtils.isEmpty(body.getData().getUser().get(i).getList().get(j).getUsername())) {
                    body.getData().getUser().get(i).getList().get(j).setUsername("null");
                }
                SortModelInfo sortModelInfo = new SortModelInfo();
                sortModelInfo.setName(body.getData().getUser().get(i).getList().get(j).getUsername());
                sortModelInfo.setIconUrl(body.getData().getUser().get(i).getList().get(j).getHeadimg());
                sortModelInfo.setId(body.getData().getUser().get(i).getList().get(j).getUserid());
                sortModelInfo.setPost(body.getData().getUser().get(i).getList().get(j).getPost());
                if (checkedUserId != 0) {
                    if (body.getData().getUser().get(i).getList().get(j).getUserid() == checkedUserId) {
                        sortModelInfo.type = sortModelInfo.TYPE_CHECKED;
                    } else {
                        sortModelInfo.type = sortModelInfo.TYPE_NOCHECKED;
                    }
                }
                String pinyin = characterParser.getSelling(body.getData().getUser().get(i).getList().get(j).getUsername());
                String sortString = pinyin.substring(0, 1).toUpperCase();

                if (sortString.matches("[A-Z]")) {
                    sortModelInfo.setSortLetters(sortString.toUpperCase());
                } else {
                    sortModelInfo.setSortLetters("#");
                }

                mSortList.add(sortModelInfo);

            }


        }
        return mSortList;

    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {

        SortModelInfo sortModelInfo = null;
        if (cbCheckedSupervisor.isChecked()) {//选中了一级主管
            sortModelInfo = new SortModelInfo();
            sortModelInfo.setId(0);
        } else {
            for (int i = 0; i < sortModelInfos.size(); i++) {
                if (sortModelInfos.get(i).type == sortModelInfos.get(i).TYPE_CHECKED) {
                    sortModelInfo = new SortModelInfo();
                    sortModelInfo.setId(sortModelInfos.get(i).getId());
                    sortModelInfo.setName(sortModelInfos.get(i).getName());
                    sortModelInfo.setIconUrl(sortModelInfos.get(i).getIconUrl());
                    sortModelInfo.setPost(sortModelInfos.get(i).getPost());
                }
            }
        }
        Log.e("选择的人员", sortModelInfo.toString());
        if (sortModelInfo == null) {
            Toasty.error(SelectSupervisorOrStaffActivity.this, "请至少选择一名人员").show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("user", (Serializable) sortModelInfo);
        setResult(Constant.RESULT_CODE, intent);
        finish();

    }

}
