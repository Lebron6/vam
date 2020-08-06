package com.dy.vam.ui.widget;

import android.app.Activity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.callback.OnTimeSelectedListener;

import java.util.List;

/**
 * Created by James on 2018/1/16.
 * 从底部选择
 */
public class ButtomSelectWindow extends PopupWindow{

    private  ListView lv_quest;
    private  SimpleAdapter adapter;
    private  PopupWindow popupWindow;
    private Activity context;

    public ButtomSelectWindow(Activity context) {
        this.context=context;
    }

    /**
     * @param adapter
     */
    public  void showView(View viewShowPop,ArrayAdapter adapter,final OnTimeSelectedListener listener) {
        final View contentView = LayoutInflater.from(context).inflate(
                R.layout.window_depart, null);
        lv_quest = (ListView) contentView.findViewById(R.id.list_ques);
        lv_quest.setAdapter(adapter);
        lv_quest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.select(position);
                popupWindow.dismiss();
            }
        });
        contentView.measure(0, 0);
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        // 使其聚集
        popupWindow.setFocusable(true);

        popupWindow.setWidth((int ) viewShowPop.getWidth());
        contentView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = contentView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()== MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.color.Trans));
popupWindow.showAtLocation(viewShowPop, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }


}
