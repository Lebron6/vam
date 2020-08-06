package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;

import java.util.HashMap;
import java.util.List;

import static com.dy.vam.config.Constant.OperaConstant.VIEW_DISTRIBUTION;

/**
 * 默认的columnAdapter
 * <p>
 * 之所以重写是为了根据content的item之高度动态设置column的item之高度
 */
public class ColumnAdapter extends ArrayAdapter {

    private Context context;
    private int resourceId;
    private List<String> columnDataList;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    private String type;

   public ColumnAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects, String type) {
        super(context, resource, objects);
        resourceId = resource;
        columnDataList = objects;
        this.context = context;
        this.type = type;
       isSelected = new HashMap<Integer, Boolean>();
       initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < columnDataList.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resourceId, null);
            holder.cbChecked = (CheckBox) convertView.findViewById(R.id.cb_checked);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_name);
            holder.layoutTitle = (RelativeLayout) convertView.findViewById(R.id.layout_title);
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams((int)context.getResources().getDimension(R.dimen.x154), (int) context.getResources().getDimension(R.dimen.x82));
            holder.layoutTitle.setLayoutParams(lp);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (type.equals(VIEW_DISTRIBUTION)) {
            holder.cbChecked.setVisibility(View.GONE);

        }else{
            holder.cbChecked.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(columnDataList.get(position))){
            holder.tvTitle.setText("null     ");
        }else{
            if (columnDataList.get(position).length()==2){
                holder.tvTitle.setText(columnDataList.get(position)+"  ");
            }else{
                holder.tvTitle.setText(columnDataList.get(position));
            }
        }
        // 根据isSelected来设置checkbox的选中状况
        holder.cbChecked.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static class Holder {
        public CheckBox cbChecked;
        TextView tvTitle;
        RelativeLayout layoutTitle;
    }
}
