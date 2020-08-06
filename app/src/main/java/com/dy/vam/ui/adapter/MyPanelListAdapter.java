package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.GetValueData;
import com.dy.vam.ui.widget.PanelListLayout;


/**
 * <pre>
 *     author : zyb
 *     e-mail : hbdxzyb@hotmail.com
 *     time   : 2017/05/23
 *     desc   : 整个页面的Adapter，内部使用了两个子Adapter
 *              开发者可自行定义两个子Adapter
 *     version: 1.0
 * </pre>
 * @author zyb
 */

public class MyPanelListAdapter extends AbstractPanelListAdapter {

    private Context context;

    private ListView lv_content;
    private int contentResourceId;
    private GetValueData getValueData = new GetValueData();

    /**
     * constructor
     *
     * @param context 上下文
     * @param pl_root 根布局（PanelListLayout）
     * @param lv_content content 部分的布局（ListView）
     * @param contentResourceId content 部分的 item 布局
     * @param getValueData content 部分的数据
     */
    public MyPanelListAdapter(Context context, PanelListLayout pl_root, ListView lv_content,
                              int contentResourceId, GetValueData getValueData) {
        super(context, pl_root, lv_content);
        this.context = context;
        this.lv_content = lv_content;
        this.contentResourceId = contentResourceId;
        this.getValueData = getValueData;
    }

    /**
     * 给该方法添加实现，返回Content部分的适配器
     *
     * @return adapter of content
     */
    @Override
    protected BaseAdapter getContentAdapter() {
        return new ContentAdapter(context,contentResourceId,getValueData);
    }

    /**
     * content部分的adapter
     *
     * 这里可以自由发挥，和普通的 ListView 的 Adapter 没区别
     */
    private class ContentAdapter extends ArrayAdapter {

        private GetValueData getValueData;
        private int resourceId;

        ContentAdapter(Context context, int resourceId, GetValueData getValueData) {
            super(context, resourceId);
            this.getValueData = getValueData;
            this.resourceId = resourceId;
        }

        @Override
        public int getCount() {
            return getValueData.getData().getList().size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            GetValueData.DataBean.ListBean listBean = getValueData.getData().getList().get(position);
            Log.d("ybz-get", "getView: Content getView");
            View view;
            ViewHolder viewHolder;

            if (convertView == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.tv_01.setText(listBean.getDepartment());
            viewHolder.tv_02.setText(listBean.getFirst());
            viewHolder.tv_03.setText(listBean.getSecond());
            viewHolder.tv_04.setText(listBean.getThird());
            viewHolder.tv_05.setText(listBean.getFourth());
            viewHolder.tv_06.setText(listBean.getTotal());
            viewHolder.tv_07.setText(listBean.getAllot());
            viewHolder.tv_08.setText(listBean.getDistrubution());
            viewHolder.tv_09.setText(listBean.getBalance());


            if (lv_content.isItemChecked(position)){
                view.setBackgroundColor(context.getResources().getColor(R.color.colorTheme));
            } else {
                view.setBackgroundColor(context.getResources().getColor(R.color.colorDeselected));
            }
            Log.d("ybz", "getView: itemview = "+ view.toString());

            return view;
        }

        private class ViewHolder {
            TextView tv_01;
            TextView tv_02;
            TextView tv_03;
            TextView tv_04;
            TextView tv_05;
            TextView tv_06;
            TextView tv_07;
            TextView tv_08;
            TextView tv_09;


            ViewHolder(View itemView) {
                tv_01 = (TextView) itemView.findViewById(R.id.id_tv_01);
                tv_02 = (TextView) itemView.findViewById(R.id.id_tv_02);
                tv_03 = (TextView) itemView.findViewById(R.id.id_tv_03);
                tv_04 = (TextView) itemView.findViewById(R.id.id_tv_04);
                tv_05 = (TextView) itemView.findViewById(R.id.id_tv_05);
                tv_06 = (TextView) itemView.findViewById(R.id.id_tv_06);
                tv_07 = (TextView) itemView.findViewById(R.id.id_tv_07);
                tv_08 = (TextView) itemView.findViewById(R.id.id_tv_08);
                tv_09 = (TextView) itemView.findViewById(R.id.id_tv_09);

            }
        }
    }
}
