package com.dy.vam.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.dy.vam.R;

import java.util.List;

public class SpnTimeAdapter extends BaseAdapter implements SpinnerAdapter {
    private Context context ;
    private List<String> list;
      
    public SpnTimeAdapter(Context context, List<String> list){
        this.context=context;  
        this.list=list;  
    }   
    @Override  
    public int getCount() {  
        return list.size();  
    }  
  
    @Override  
    public Object getItem(int position) {  
        return list.get(position);  
    }  
  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_spn_time, null);
        TextView tvgetView=(TextView) convertView.findViewById(R.id.tvgetView);
        tvgetView.setText(getItem(position).toString());  
        return convertView;  
    }  

}  