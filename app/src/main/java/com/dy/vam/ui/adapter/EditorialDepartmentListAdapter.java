package com.dy.vam.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.Organization;

import static com.dy.vam.config.Constant.OperaConstant.MANAGE_ORGANIZATION;

/**
 * Created by James on 2018/3/27.
 */

public class EditorialDepartmentListAdapter extends BaseAdapter {

    private Context context;
    private Organization organization;
    private  String type;

    public void setData(Organization organization) {
        this.organization = organization;
    }

//    public EditorialDepartmentListAdapter(Context context, OnSlideMenuClickListener onSlideMenuClickListener,String type) {
    public EditorialDepartmentListAdapter(Context context,String type) {
        this.context = context;
//        this.onSlideMenuClickListener = onSlideMenuClickListener;
        this.type = type;
    }

    @Override
    public int getCount() {
        return organization.getData().getDepartment().size();
    }

    @Override
    public Object getItem(int position) {
        return organization.getData().getDepartment().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if (convertView == null) {
//            if (type.equals(MANAGE_ORGANIZATION)) {
//                convertView = new DragDelItem( View.inflate(context,
//                        R.layout.item_editorial_department, null), View.inflate(context,
//                        R.layout.slide_view_merge, null));
//            }else{
                convertView=View.inflate(context,
                        R.layout.item_editorial_department, null);
//            }
            viewHolder=new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvDepartName.setText(organization.getData().getDepartment().get(position).getName()+"("+organization.getData().getDepartment().get(position).getCount()+")");
//        viewHolder.tvDepartName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DepartmentStaffListActivity.actionStart(context, "部门成员", organization.getData().getDepartment().get(position).getDepartmentid(), type);
//
//            }
//        });
//        if (type.equals(MANAGE_ORGANIZATION)){
//            viewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onSlideMenuClickListener.onSlideDeleteClickListener(position);
//                }
//            });
//            viewHolder.tvEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onSlideMenuClickListener.onSlideEditClickListener(position);
//                }
//            });
//
//        }
        return convertView;
    }
    class ViewHolder {

        public ViewHolder(View convertView) {
            tvDepartName = (TextView) convertView.findViewById(R.id.tv_depart_name);
            if (type.equals(MANAGE_ORGANIZATION)){
                tvDelete = (TextView) convertView.findViewById(R.id.tv_delete);
                tvEdit = (TextView) convertView.findViewById(R.id.tv_edit);
            }
        }
        public TextView tvEdit;
        public TextView tvDelete;
        public TextView tvDepartName;
    }

//    private OnSlideMenuClickListener onSlideMenuClickListener;
//
//    public interface OnSlideMenuClickListener{
//        void onSlideDeleteClickListener(int position);
//        void onSlideEditClickListener(int position);
//    }

}
