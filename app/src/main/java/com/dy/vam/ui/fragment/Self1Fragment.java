package com.dy.vam.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dy.vam.BuildConfig;
import com.dy.vam.R;
import com.dy.vam.api.BaseUrl;
import com.dy.vam.entity.SummaryGetPersonData;
import com.dy.vam.entity.Verif;
import com.dy.vam.config.Constant;
import com.dy.vam.tools.FastBlur;
import com.dy.vam.tools.FileUtil;
import com.dy.vam.tools.GlideCircleTransform;
import com.dy.vam.tools.PreferenceUtils;
import com.dy.vam.tools.Utils;
import com.dy.vam.ui.activity.ClipImageActivity;
import com.dy.vam.ui.activity.SettingActivity;
import com.dy.vam.ui.activity.WebViewActivity;
import com.dy.vam.ui.widget.UpDateIconPop;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.dy.vam.tools.FileUtil.getRealFilePathFromUri;


/**
 * Created by James on 2018/1/9.
 * 我的
 */

public class Self1Fragment extends BaseFragment{

    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_total_individual_value)
    TextView tvTotalIndividualValue;
    @BindView(R.id.tv_allocated_amount)
    TextView tvAllocatedAmount;
    @BindView(R.id.tv_amount_to_be_allocated)
    TextView tvAmountToBeAllocated;
    @BindView(R.id.layout_total_details)
    LinearLayout layoutTotalDetails;
    @BindView(R.id.tv_total_amount_of_production)
    TextView tvTotalAmountOfProduction;
    @BindView(R.id.tv_commission_allocated)
    TextView tvCommissionAllocated;
    @BindView(R.id.tv_not_allocated)
    TextView tvNotAllocated;
    @BindView(R.id.layout_commission_details)
    LinearLayout layoutCommissionDetails;
    @BindView(R.id.layout_my_panel)
    RelativeLayout layoutMyPanel;
    @BindView(R.id.view_show_pop)
    View viewShowPop;
    @BindView(R.id.tv_total_type)
    TextView tvTotalType;
    @BindView(R.id.tv_production_type)
    TextView tvProductionType;
    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    @BindView(R.id.layout_top_header)
    RelativeLayout layoutTopHeader;
    @BindView(R.id.layout_size)
    LinearLayout layoutSize;
    @BindView(R.id.image_setting)
    ImageView imageSetting;
    private SummaryGetPersonData summaryGetPersonData;
    private UpDateIconPop upDateIconPop;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private int type = 1;
    private File tempFile;

    private int scaleRatio = 20;

    private int blurRadius = 1;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_self_one;
    }

    @Override
    protected void initViews() {

    }

    private void loadPersonDataByTime() {
        Log.e("根据时间个人信息",PreferenceUtils.getInstance().getDefaultYear()+"");
        createRequest(BaseUrl.getInstence().getSummaryGetPersonDataUrl()).summaryGetPersonData(PreferenceUtils.getInstance().getUserToken(), PreferenceUtils.getInstance().getDefaultYear()).enqueue(new Callback<SummaryGetPersonData>() {
            @Override
            public void onResponse(Call<SummaryGetPersonData> call, Response<SummaryGetPersonData> response) {
                summaryGetPersonData = response.body();
                if (summaryGetPersonData != null) {
                    if (summaryGetPersonData.getCode() == Constant.SUCCESS) {
                        if (summaryGetPersonData.getData() != null) {
                            if (summaryGetPersonData.getData().getUser() != null) {
                                if (summaryGetPersonData.getData().getUser().getRole() == Constant.Role.CEO||summaryGetPersonData.getData().getUser().getRole()==Constant.Role.EXECUTIVE) {
                                    layoutMyPanel.setVisibility(View.GONE);
                                }
                                Glide.with(getActivity()).load(summaryGetPersonData.getData().getUser().getHeadimg()).bitmapTransform(new GlideCircleTransform(getActivity())).into(userIcon);
                                Glide.with(getActivity()).load(summaryGetPersonData.getData().getUser().getHeadimg()).asBitmap().into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(resource,
                                                resource.getWidth() / scaleRatio,
                                                resource.getHeight() / scaleRatio,
                                                false);
                                        layoutHeader.setBackground(new BitmapDrawable( FastBlur.doBlur(scaledBitmap, blurRadius, true)));
                                    }
                                }); //方法中设置asBitmap可以设置回调类型

                                String userName = summaryGetPersonData.getData().getUser().getUsername();
                                String position = summaryGetPersonData.getData().getUser().getPost();
                                tvUserName.setText(TextUtils.isEmpty(userName) ? "无名" : summaryGetPersonData.getData().getUser().getUsername());
                                tvPosition.setText(TextUtils.isEmpty(position) ? "无职业" : summaryGetPersonData.getData().getUser().getPost());
                                if (summaryGetPersonData.getData().getUser().getRole() == 2) {
                                    tvTotalType.setText("公司可分配价值总额：");
                                    tvProductionType.setText("公司提成总额：");
                                }
                            }
                            if (summaryGetPersonData.getData().getValue() != null) {
                                tvTotalIndividualValue.setText("￥"+summaryGetPersonData.getData().getValue().getTotal());
                                tvAllocatedAmount.setText("￥"+summaryGetPersonData.getData().getValue().getAllot());
                                tvAmountToBeAllocated.setText("￥"+summaryGetPersonData.getData().getValue().getNoallot());
                            }
                            if (summaryGetPersonData.getData().getCommission() != null) {
                                tvTotalAmountOfProduction.setText("￥"+summaryGetPersonData.getData().getCommission().getTotal());
                                tvCommissionAllocated.setText("￥"+summaryGetPersonData.getData().getCommission().getAllot());
                                tvNotAllocated.setText("￥"+summaryGetPersonData.getData().getCommission().getNoallot());
                            }
                        }
                    } else {
                        Toasty.error(getActivity(), summaryGetPersonData.getMsg()).show();
                    }
                } else {
                    Toasty.error(getActivity(), getString(R.string.net_error) + ":获取获取个人首页信息失败").show();
                }

            }

            @Override
            public void onFailure(Call<SummaryGetPersonData> call, Throwable t) {
                Toasty.error(getActivity(), getString(R.string.net_error) + ":获取获取个人首页信息失败").show();
                Log.e("获取获取个人首页信息失败:", t.toString());
            }
        });
    }

    @Override
    protected void initDatas() {
        loadPersonDataByTime();
    }

    @OnClick({R.id.user_icon, R.id.image_setting, R.id.layout_total_details, R.id.layout_commission_details, R.id.layout_my_panel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                upDateIconPop = new UpDateIconPop(getActivity(), itemsOnClick);
                upDateIconPop.showAtLocation(viewShowPop, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.image_setting:
                SettingActivity.actionStart(getActivity(), "设置");
                break;
            case R.id.layout_total_details:
                if (summaryGetPersonData != null && summaryGetPersonData.getData() != null && summaryGetPersonData.getData().getValue().getUrl() != null) {
                    WebViewActivity.actionStart(getActivity(), "", summaryGetPersonData.getData().getValue().getUrl(),true);
                } else {
                    Toasty.error(getActivity(), getString(R.string.net_error) + ":获取可分配详情Url失败").show();
                }
                break;
            case R.id.layout_commission_details:
                if (summaryGetPersonData != null && summaryGetPersonData.getData() != null && summaryGetPersonData.getData().getCommission().getUrl() != null) {
                    WebViewActivity.actionStart(getActivity(), "", summaryGetPersonData.getData().getCommission().getUrl(),true);
                } else {
                    Toasty.error(getActivity(), getString(R.string.net_error) + ":获取提成详情Url失败").show();
                }
                break;
            case R.id.layout_my_panel:
                if (summaryGetPersonData != null && summaryGetPersonData.getData() != null && summaryGetPersonData.getData().getUser().getTask_url() != null) {
                    WebViewActivity.actionStart(getActivity(), "", summaryGetPersonData.getData().getUser().getTask_url());
                } else {
                    Toasty.error(getActivity(), getString(R.string.net_error) + ":获取任务面板Url失败").show();
                }
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_photo:                   //拍照取图
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请WRITE_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到调用系统相机
                        gotoCamera();
                    }
                    // 3、调用拍照方法
                    break;
                case R.id.btn_chose:
                    // 3、调用从图库选取图片方法
                    //权限判断
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        //申请READ_EXTERNAL_STORAGE权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    } else {
                        //跳转到相册
                        gotoPhoto();
                    }
                    break;
            }
            upDateIconPop.dismiss();
        }
    };

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Log.e("参数",BuildConfig.APPLICATION_ID + ".fileProvider");
            Uri contentUri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    final String cropImagePath = getRealFilePathFromUri(getActivity(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    createRequest(BaseUrl.getInstence().getUserUpdataIconUrl()).updataUserIcon(PreferenceUtils.getInstance().getUserToken(), Utils.bitmapToBase64(bitMap)).enqueue(new Callback<Verif>() {
                        @Override
                        public void onResponse(Call<Verif> call, Response<Verif> response) {
                            Log.e("onResponse：", response.body().toString());
                            switch (response.body().getCode()) {
                                case Constant.SUCCESS:
                                    Toasty.info(getActivity(), "头像修改成功").show();
                                    Glide.with(getActivity()).load(cropImagePath).bitmapTransform(new GlideCircleTransform(getActivity())).into(userIcon);
                                    Glide.with(getActivity()).load(cropImagePath).asBitmap().into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            Bitmap scaledBitmap = Bitmap.createScaledBitmap(resource,
                                                    resource.getWidth() / scaleRatio,
                                                    resource.getHeight() / scaleRatio,
                                                    false);
                                            layoutHeader.setBackground(new BitmapDrawable( FastBlur.doBlur(scaledBitmap, blurRadius, true)));
                                        }
                                    }); //方法中设置asBitmap可以设置回调类型
                                    break;
                                default:
                                    Toasty.info(getActivity(), response.body().getMsg()).show();
                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<Verif> call, Throwable t) {
                            Toasty.warning(getActivity(), getString(R.string.net_error) + ":修改头像失败！").show();
                            Log.e("修改头像", t.toString());
                        }
                    });
                }
                break;
        }
    }


    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getActivity(), ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

}
