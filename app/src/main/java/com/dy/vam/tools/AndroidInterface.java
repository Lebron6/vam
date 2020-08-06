package com.dy.vam.tools;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.dy.vam.ui.activity.SelectSupervisorOrStaffActivity;
import com.just.agentweb.AgentWeb;

import me.nereo.multi_image_selector.MultiImageSelector;

import static com.dy.vam.config.Constant.REQUEST_IMAGE;


/**
 * Created by James on 2018/1/15.
 */

public class AndroidInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private Activity context;
    private View view;

    public AndroidInterface(AgentWeb agent, Activity context, View view) {
        this.context = context;
        this.view = view;
    }

    @JavascriptInterface
    public void closeWindow() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                context.finish();
            }
        });
    }

    @JavascriptInterface
    public void chooseUser(final int userId,final int type) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                SelectSupervisorOrStaffActivity.actionStartForResult(context, "请选择", userId,type);
            }
        });
    }

    @JavascriptInterface
    public void addButton() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    @JavascriptInterface
    public void deleteButton() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        });
    }

    @JavascriptInterface
    public void uploadInvoiceImg(int type) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                pickImage();
            }
        });
    }

    private void pickImage() {
        MultiImageSelector selector = MultiImageSelector.create(context);
        selector.showCamera(true).count(3) // 最大选择图片数量, 默认为3. 只有在选择模式为多选时有效
        .multi().// 多选模式, 默认模式;
        start(context, REQUEST_IMAGE);
    }

}
