package com.dy.vam.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dy.vam.R;
import com.dy.vam.entity.SortModelInfo;
import com.dy.vam.tools.GlideCircleTransform;

import java.util.List;


public class StaffMultiselectAdapter extends BaseAdapter implements SectionIndexer{


	private List<SortModelInfo> list = null;
	private Context mContext;

	public StaffMultiselectAdapter(Context mContext, List<SortModelInfo> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	public void updateListView(List<SortModelInfo> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final SortModelInfo mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_staff_multiselect, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_name);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.imageIcon = (ImageView) view.findViewById(R.id.image_icon);
			viewHolder.checkBox = (CheckBox) view.findViewById(R.id.cb_checked);
			viewHolder.tvPosition = (TextView) view.findViewById(R.id.tv_position);
			viewHolder.relativeLayout = (RelativeLayout) view.findViewById(R.id.layout_title);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		int section = getSectionForPosition(position);

		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
        Glide.with(mContext).load(list.get(position).getIconUrl()).bitmapTransform(new GlideCircleTransform(mContext)).into(viewHolder.imageIcon);
		viewHolder.tvTitle.setText(list.get(position).getName()+"");
		viewHolder.tvPosition.setText(list.get(position).getPost()+"");

		viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (list.get(position).type == list.get(position).TYPE_CHECKED){
					list.get(position).type =  list.get(position).TYPE_NOCHECKED;
				} else {
					list.get(position).type = list.get(position).TYPE_CHECKED;
				}
				//每次点击后数据更新   但checkbox不会随Item点击发生选中状态变化，notifyDataSetChanged();之后数据与View视图同步
				notifyDataSetChanged();
			}
		});
		if(list.get(position).type ==list.get(position).TYPE_CHECKED){
			viewHolder.checkBox.setChecked(true);
		}else{
			viewHolder.checkBox.setChecked(false);
		}


		return view;

	}
	


	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
        ImageView imageIcon;
		CheckBox checkBox;
		TextView tvPosition;
		RelativeLayout relativeLayout;
	}

	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}