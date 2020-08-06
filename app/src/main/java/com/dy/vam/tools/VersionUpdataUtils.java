package com.dy.vam.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dy.vam.BuildConfig;
import com.dy.vam.R;
import com.dy.vam.application.VAMApplication;
import com.dy.vam.ui.widget.VersionUpdateDialog;

import java.io.File;
import java.math.BigDecimal;

import okhttp3.Request;

import static com.dy.vam.tools.DownloadManager.getApkFile;

/**
 * Created by Administrator on 2017/10/23.
 */

public class VersionUpdataUtils {
    /**
     * @param ver
     * @param url
     * @param act
     * @return
     */
    public static int versionVerification(String ver, String url, Activity act) {
        try {
            VAMApplication app = (VAMApplication) VAMApplication
                    .getApplication();

            int localverison = app.getPackageManager().getPackageInfo(
                    app.getPackageName(), 0).versionCode;
            float serververison = Float.parseFloat(ver);// null不能转化为flat
            Log.e("本地版本号：", "" + localverison);
            Log.e("服务器版本号：", "" + serververison);
            if (ver.equals("null") || ver.equals("")) {
                ver = "" + localverison;
            }

            if (serververison > localverison) {
                return 1;
            } else if (serververison == localverison) {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
        return 4;
    }

    /**
     * @param url
     * @param a
     * @param b
     */
    private static VersionUpdateDialog commonDialog4;
    private static TextView size;
    private static TextView title;
    private static TextView spend;
    private static ProgressBar progressBar;
    private static TextView message;
    private static Button dialog_button_cancel;
    private static Button dialog_button_ok;
    private static View pro;


    public static void update(final String url, final Activity a, final int b) {
        commonDialog4 = new VersionUpdateDialog(a, R.style.MyDialog);
        View view = commonDialog4.getView();
        size = (TextView) view.findViewById(R.id.size);
        message = (TextView) view.findViewById(R.id.message);
        title = (TextView) view.findViewById(R.id.title);
        spend = (TextView) view.findViewById(R.id.spend);
        dialog_button_cancel = (Button) view.findViewById(R.id.dialog_button_cancel);
        dialog_button_ok = (Button) view.findViewById(R.id.dialog_button_ok);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        pro = view.findViewById(R.id.pro);

        if (b == 1) {
            title.setText("更新提醒"+"("+Utils.getLocalVersionName(a)+")");
            message.setText(a.getString(R.string.v_ud));
            dialog_button_ok.setText("更新");
        } else {
            title.setText("版本更新");
            message.setText("恭喜您！程序已是最新版本，无需更新~");
            dialog_button_ok.setText("确定");
        }
        dialog_button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (b == 1) {
                    message.setVisibility(View.INVISIBLE);
                    pro.setVisibility(View.VISIBLE);
                    dialog_button_ok.setVisibility(View.GONE);
                    startDown(url);
                } else {
                    commonDialog4.dismiss();
                }
            }
        });

        dialog_button_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DownloadManager.cancleDown();
                commonDialog4.dismiss();
                a.finish();
            }
        });

        commonDialog4.show();

    }


    //下载
    protected static void startDown(String url) {

        DownloadManager.downloadFile(url, "vam", new DownloadManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                message.setText("下载失败");
            }

            @Override
            public void onResponse(Object response) {
                commonDialog4.dismiss();
                File apkFile = getApkFile("vam");
                installApp(VAMApplication.getApplication(), apkFile);
            }

            @Override
            public void onProgress(double total, double current) {
                progressBar.setProgress(getInt((current / total) * 100));
            }
        });
    }

    public static int getInt(double number) {
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }

    /**
     * 安装应用程序
     *
     * @param context
     * @param apkFile
     */
    public static void installApp(Context context, File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
//判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

}
