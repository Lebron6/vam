package com.dy.vam.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.ui.activity.WebViewActivity;

/**
 * Created by James on 2016/6/29.
 */
public class TitleBarManger {

    private Activity context;
    private View back;
    private View question;
    private TextView titlemsg;
    private View layout;
    private static TitleBarManger titleBarManger=null;

    /**
     * 获取一个实例
     * @return
     */
    public static TitleBarManger getInsetance() {
        if (titleBarManger==null){
            titleBarManger= new TitleBarManger();
        }
        return  titleBarManger;
    }

    /**
     * set一个上下文
     *
     * @param con
     */
    public void setContext(Activity con) {
        this.context = con;
    }


    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        titlemsg = (TextView)context.findViewById(findId(context, "toolbar_title"));
        titlemsg.setText(title);
    }

    /**
     * 设置标题
     * @param colorResources
     */
    public void setTitleTextColor(int colorResources) {
        titlemsg.setTextColor(colorResources);
    }

    /**
     * 设置颜色
     * @param colorResources
     */
    public void setThemeBGColor(int colorResources) {
        layout=context.findViewById(findId(context,"toolBar"));
        layout.setBackgroundResource(colorResources);
    }

    public void setBack(){
        back = context.findViewById(findId(context, "layout_back"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

    public void setQuestion(final String url){
        question = context.findViewById(findId(context, "layout_question"));
        question.setVisibility(View.VISIBLE);
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.actionStart(context,"名词解释",url);
            }
        });
    }

    /**
     * 根据上下文获取资源ID
     *
     * @param context
     * @param id
     * @return
     */
    public int findId(Context context, String id) {
        if (context != null) {
            Resources resource = context.getResources();
            return resource.getIdentifier(id, "id", context.getPackageName());
        }
        return 0;
    }

}
