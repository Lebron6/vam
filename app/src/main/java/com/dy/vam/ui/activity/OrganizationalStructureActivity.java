package com.dy.vam.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.Organization;
import com.dy.vam.entity.SortModelInfo;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.CharacterParser;
import com.dy.vam.tools.PinyinComparator;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.adapter.EditorialDepartmentListAdapter;
import com.dy.vam.ui.adapter.StaffListAdapter;
import com.dy.vam.ui.widget.DepartOperaDialog;
import com.dy.vam.ui.widget.LoadDialog;
import com.dy.vam.ui.widget.TipsDialog;
import com.dy.vam.ui.widget.TitleBarManger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dy.vam.config.Constant.OperaConstant.MANAGE_ORGANIZATION;
import static com.dy.vam.config.Constant.OperaConstant.VIEW_ORGANIZATION;

/**
 * Created by James on 2018/1/30.
 * 组织架构
 */

public class OrganizationalStructureActivity extends BaseActivity implements DepartOperaDialog.DialogClickListener {

    public static final String DETAILS_VALUES = "title";
    public static final String TYPE = "type";
    @BindView(R.id.rv_all_depart)
    ListView lvAllDepart;
    //    DragDelListView lvAllDepart;
    @BindView(R.id.lv_staff)
    ListView lvStaff;
    @BindView(R.id.sv_organizationa_structure)
    ScrollView svOrganizationaStructure;
    @BindView(R.id.layout_add_depart)
    LinearLayout layoutAddDepart;
    private List<SortModelInfo> SourceDateList;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private EditorialDepartmentListAdapter editorialDepartMentAdapter;
    private StaffListAdapter adapter;
    private Organization organization;
    private String type;
    private TipsDialog deleteDepartDialog;
    private TitleBarManger manger;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }

    public static void actionStart(Context context, String title, String type) {
        Intent intent = new Intent(context, OrganizationalStructureActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle(getIntent().getStringExtra(DETAILS_VALUES));
        manger.setBack();
        loadDatas();    //放在OnResume();里是因为添加成员或部门后 视图会更新
    }

    private void loadDatas() {
        LoadDialog.show(this);
        createRequest(BaseUrl.getInstence().getOrganizationUrl()).getOrganization(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<Organization>() {
            @Override
            public void onResponse(Call<Organization> call, Response<Organization> response) {
                LoadDialog.dismiss(OrganizationalStructureActivity.this);
                organization = response.body();
                switch (organization.getCode()) {
                    case 0:
                        //初始化部门列表数据
                        if (organization.getData() != null) {
                            manger.setTitle(organization.getData().getCompany());
                            //根据部门成员名字排序        //排在前面 不然员工ListView会抢焦点
                            SourceDateList = filledData(organization);
                            Collections.sort(SourceDateList, pinyinComparator);
                            adapter = new StaffListAdapter(OrganizationalStructureActivity.this, SourceDateList);
                            lvStaff.setAdapter(adapter);
                            Utils.setListView(lvStaff);
                            lvStaff.setFocusable(false);

                            editorialDepartMentAdapter.setData(organization);
                            lvAllDepart.setAdapter(editorialDepartMentAdapter);
                            Utils.setListView(lvAllDepart);
                            lvAllDepart.setFocusable(false);
                            lvAllDepart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    DepartmentStaffListActivity.actionStart(OrganizationalStructureActivity.this, organization.getData().getDepartment().get(position).getName(), organization.getData().getDepartment().get(position).getDepartmentid(), type);
                                }
                            });
                            if (getIntent().getStringExtra(TYPE).equals(VIEW_ORGANIZATION)) {

                            } else {
                                lvAllDepart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                    @Override
                                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                        DepartOperaDialog departOperaDialog = new DepartOperaDialog(OrganizationalStructureActivity.this, R.style.MyDialog, OrganizationalStructureActivity.this, position);
                                        departOperaDialog.show();
                                        return true;
                                    }
                                });
                            }

                            lvStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    StaffDetailsActivity.actionStart(OrganizationalStructureActivity.this, ((SortModelInfo) adapter.getItem(position)).getId(), type);
                                }
                            });
                        } else {
                            Toasty.error(OrganizationalStructureActivity.this, "获取组织架构数据失败").show();
                        }

                        break;
                    default:
                        Toasty.error(OrganizationalStructureActivity.this, response.body().getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Organization> call, Throwable t) {
                LoadDialog.dismiss(OrganizationalStructureActivity.this);
                Toasty.error(OrganizationalStructureActivity.this, getString(R.string.net_error) + ":获取组织架构数据失败！").show();
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_organizationa_structure;
    }

    @Override
    protected void initViews() {
        type = getIntent().getStringExtra(TYPE);
        if (type.equals(MANAGE_ORGANIZATION)) {
            layoutAddDepart.setVisibility(View.VISIBLE);
        } else {
            layoutAddDepart.setVisibility(View.GONE);
        }
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        editorialDepartMentAdapter = new EditorialDepartmentListAdapter(OrganizationalStructureActivity.this, getIntent().getStringExtra(TYPE));
//        lvAllDepart.setType(getIntent().getStringExtra(TYPE));
    }

    @Override
    protected void initDatas() {

    }

    private List<SortModelInfo> filledData(Organization body) {

        List<SortModelInfo> mSortList = new ArrayList<>();
        for (int i = 0; i < body.getData().getUser().size(); i++) {
            for (int j = 0; j < body.getData().getUser().get(i).getList().size(); j++) {
                if (!TextUtils.isEmpty(body.getData().getUser().get(i).getList().get(j).getUsername())) {
                    SortModelInfo sortModelInfo = new SortModelInfo();
                    sortModelInfo.setName(body.getData().getUser().get(i).getList().get(j).getUsername());
                    sortModelInfo.setIconUrl(body.getData().getUser().get(i).getList().get(j).getHeadimg());
                    sortModelInfo.setId(body.getData().getUser().get(i).getList().get(j).getUserid());
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
        }
        return mSortList;
    }

    @OnClick(R.id.layout_add_depart)
    public void onViewClicked() {
        AddDepartmentActivity.actionStart(OrganizationalStructureActivity.this, "增加部门");
    }

//    @Override
//    public void onSlideDeleteClickListener(int position) {
//        LoadDialog.show(OrganizationalStructureActivity.this);
//        createRequest(BaseUrl.getInstence().getDeleteDepartNameUrl()).deleteDepartment(PreferenceUtils.getInstance().getUserToken(),organization.getData().getDepartment().get(position).getDepartmentid()).enqueue(callback);
//    }
//
//    @Override
//    public void onSlideEditClickListener(int position) {
//        UpdateDepartmentActivity.actionStart(OrganizationalStructureActivity.this,"编辑部门",organization.getData().getDepartment().get(position).getDepartmentid(),organization.getData().getDepartment().get(position).getName());
//    }

    Callback<Verif> callback = new Callback<Verif>() {
        @Override
        public void onResponse(Call<Verif> call, Response<Verif> response) {
            if (deleteDepartDialog != null) {
                deleteDepartDialog.dismiss();
            }
            LoadDialog.dismiss(OrganizationalStructureActivity.this);
            switch (response.body().getCode()) {
                case Constant.SUCCESS:
                    Toasty.info(OrganizationalStructureActivity.this, "删除成功").show();
                    loadDatas();
                    break;
                default:
                    Toasty.error(OrganizationalStructureActivity.this, response.body().getMsg()).show();
                    break;
            }
        }

        @Override
        public void onFailure(Call<Verif> call, Throwable t) {
            if (deleteDepartDialog != null) {
                deleteDepartDialog.dismiss();
            }
            LoadDialog.dismiss(OrganizationalStructureActivity.this);
            Toasty.warning(OrganizationalStructureActivity.this, getString(R.string.net_error)).show();
            Log.e("删除部门：", t.toString());
        }
    };

    @Override
    public void edit(int position) {
        UpdateDepartmentActivity.actionStart(OrganizationalStructureActivity.this, "编辑部门", organization.getData().getDepartment().get(position).getDepartmentid(), organization.getData().getDepartment().get(position).getName());
    }

    @Override
    public void delete(final int position) {
        deleteDepartDialog = new TipsDialog(OrganizationalStructureActivity.this, "确认删除该部门吗？", new TipsDialog.DialogClickListener() {
            @Override
            public void yes() {
                LoadDialog.show(OrganizationalStructureActivity.this);
                createRequest(BaseUrl.getInstence().getDeleteDepartNameUrl()).deleteDepartment(PreferenceUtils.getInstance().getUserToken(), organization.getData().getDepartment().get(position).getDepartmentid()).enqueue(callback);
            }

            @Override
            public void no() {
                deleteDepartDialog.dismiss();
            }
        });
        deleteDepartDialog.show();
    }
}
