package com.dy.vam.tools;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class SetAlias {

    private static final String TAG = "setAliasAndTags";
    private Context context;
    private String alias;
    private String logs;

    public SetAlias(Context context, String alias) {
        this.alias = alias;
        this.context = context;
    }

    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    public void setAlias() {
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(context, (String) msg.obj, null, new TagAliasCallback() {    //arg0：Context对象arg1:别名 arg2：标签名（如果是别名推送的话，这里应该传null）TagAliasCallback：实现TagAliasCallback的组件对象， 该组件提实现 gotResult 方法。采用回调机制返回执行结果，对应的参数code区分返回的状态。如6002，为超时，延迟重试
                        @Override
                        public void gotResult(int i, String s, java.util.Set<String> set) {
                            switch (i) {
                                case 0:
                                    logs = "Set tag and alias success";
                                    Log.e(TAG, logs);
                                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                                    break;
                                case 6002:
                                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                                    Log.i(TAG, logs);
                                    // 延迟 60 秒来调用 Handler 设置别名
                                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                                    break;
                                default:
                                    logs = "Failed with errorCode = " + i;
                                    Log.e(TAG, logs);
                            }
                           // Toast.makeText(context, logs, Toast.LENGTH_SHORT).show();
                        }
                    });
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
}