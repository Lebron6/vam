package com.dy.vam.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ListView;

import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.SortModelInfo;
import com.dy.vam.entity.Users;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.CharacterParser;
import com.dy.vam.tools.PinyinComparator;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.ui.adapter.StaffMultiselectAdapter;
import com.dy.vam.ui.widget.TitleBarManger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2018/2/7.
 * 所有员工/选择界面
 */

public class SelectStaffActivity extends BaseActivity {

    public static final String DETAILS_VALUES = "title";

    @BindView(R.id.lv_staff)
    ListView lvStaff;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private CharacterParser characterParser;

    private PinyinComparator pinyinComparator;
    private List<SortModelInfo> sourceDateList;
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).statusBarView(R.id.view_status_bar)
                .init();
    }
    public static void actionStartForResult(Activity context, String title) {
        Intent intent = new Intent(context, SelectStaffActivity.class);
        intent.putExtra(DETAILS_VALUES, title);
        context.startActivityForResult(intent,Constant.REQUEST_CODE);
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
        return R.layout.activity_staff_select;
    }

    @Override
    protected void initViews() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
    }

    @Override
    protected void initDatas() {
        loadDatas();
    }
    private void loadDatas() {
        createRequest(BaseUrl.getInstence().marketGetUsersUrl()).getUsersList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users users = response.body();
                switch (users.getCode()) {
                    case 0:
                        if (users.getData()!=null){
                            sourceDateList = filledData(response.body());
                            Collections.sort(sourceDateList, pinyinComparator);
                            StaffMultiselectAdapter adapter = new StaffMultiselectAdapter(SelectStaffActivity.this,sourceDateList);
                            lvStaff.setAdapter(adapter);
                        }else{
                            Toasty.error(SelectStaffActivity.this, "获取员工列表失败").show();
                        }
                        break;
                    default:
                        Toasty.error(SelectStaffActivity.this, users.getMsg()).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toasty.error(SelectStaffActivity.this,getString(R.string.net_error)+": 获取员工列表超时").show();
            }
        });
    }
    private List<SortModelInfo> filledData(Users body) {

        List<SortModelInfo> mSortList=new ArrayList<>();
        for (int i = 0; i < body.getData().size(); i++) {
            for (int j = 0; j < body.getData().get(i).getList().size(); j++) {
                if (!TextUtils.isEmpty(body.getData().get(i).getList().get(j).getUsername())){
                    SortModelInfo sortModelInfo = new SortModelInfo();
                    sortModelInfo.setName(body.getData().get(i).getList().get(j).getUsername());
                    sortModelInfo.setIconUrl(body.getData().get(i).getList().get(j).getHeadimg());
                    sortModelInfo.setId(body.getData().get(i).getList().get(j).getUserid());
                    sortModelInfo.setPost(body.getData().get(i).getList().get(j).getPost());
                    String pinyin = characterParser.getSelling(body.getData().get(i).getList().get(j).getUsername());
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
    @OnClick(R.id.btn_commit)
    public void onViewClicked() {

        List<SortModelInfo> checkedUsers=new ArrayList<>();
        for (int i = 0; i < sourceDateList.size(); i++) {

            if (sourceDateList.get(i).type==sourceDateList.get(i).TYPE_CHECKED){
                checkedUsers.add(sourceDateList.get(i));
            }

        }
        if (checkedUsers.size()==0){
            Toasty.error(SelectStaffActivity.this,"请至少选择一名员工").show();
            return;
        }
        Intent intent=new Intent();
        intent.putExtra("users",(Serializable)checkedUsers);
        setResult(Constant.RESULT_CODE,intent);
        finish();

    }
}
