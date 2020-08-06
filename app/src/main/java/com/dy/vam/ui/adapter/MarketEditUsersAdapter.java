package com.dy.vam.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.SortModelInfo;
import com.dy.vam.callback.OnTimeSelectedListener;
import com.dy.vam.ui.widget.ButtomSelectWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class MarketEditUsersAdapter extends RecyclerView.Adapter {

    private Activity context;
    private List<SortModelInfo> sortModelInfos;
    private final ArrayAdapter arrayAdapter;
    private final List<String> timeList;
    private View viewLine;

    public MarketEditUsersAdapter(Activity context,View viewLine) {
        this.context = context;
        this.viewLine = viewLine;
        timeList = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            String percent = i +"%";
            timeList.add(percent);
        }
        arrayAdapter = new ArrayAdapter(context, R.layout.item_question, R.id.tv_popqusetion, timeList);
    }

    public void setDatas(List<SortModelInfo> sortModelInfos) {
        this.sortModelInfos = sortModelInfos;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_staff_edit_percent, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvName.setText(sortModelInfos.get(position).getName());
        if (!TextUtils.isEmpty(sortModelInfos.get(position).getPercent())){
            viewHolder.tvPercent.setText(sortModelInfos.get(position).getPercent()+"%");
        }
        viewHolder.layoutShowPercentWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arrayAdapter == null) {
                    Toasty.error(context, context.getString(R.string.net_error) + ":比例列表为空！").show();
                    return;
                }
                ButtomSelectWindow timeSelectWindow = new ButtomSelectWindow(context);
                timeSelectWindow.showView(viewLine, arrayAdapter, new OnTimeSelectedListener() {
                    @Override
                    public void select(int postion) {
                        viewHolder.tvPercent.setText(timeList.get(postion));
                        sortModelInfos.get(position).setPercent(timeList.get(postion).replace("%",""));
                    }
                });
            }
        });
viewHolder.imageRemove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sortModelInfos.remove(position);
        notifyDataSetChanged();
    }
});
    }
    @Override
    public int getItemCount() {
        return sortModelInfos.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_remove)
        ImageView imageRemove;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        @BindView(R.id.layout_show_percent_window)
        RelativeLayout layoutShowPercentWindow;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}