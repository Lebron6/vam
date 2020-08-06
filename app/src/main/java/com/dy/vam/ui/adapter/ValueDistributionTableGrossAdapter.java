package com.dy.vam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dy.vam.R;
import com.dy.vam.entity.GetValueData;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dy.vam.config.Constant.OperaConstant.VIEW_DISTRIBUTION;

public class ValueDistributionTableGrossAdapter extends RecyclerView.Adapter {


    private Context context;
    private GetValueData getValueData;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    private String type;

    public ValueDistributionTableGrossAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
        isSelected = new HashMap<Integer, Boolean>();

    }

    public void setDatas(GetValueData getValueData) {
        this.getValueData = getValueData;
        initDate();
        notifyDataSetChanged();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < getValueData.getData().getList().size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_value_distribution_table_cross_content, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        if (type.equals(VIEW_DISTRIBUTION)) {
            viewHolder.layoutCbVis.setVisibility(View.GONE);
        } else {
            viewHolder.layoutCbVis.setVisibility(View.VISIBLE);
        }
        // 根据isSelected来设置checkbox的选中状况
        viewHolder.cbSelect.setChecked(getIsSelected().get(position));
        String username = getValueData.getData().getList().get(position).getUsername();
        viewHolder.tvName.setText(TextUtils.isEmpty(username) ? "null" : username);
        viewHolder.tvDepartName.setText(getValueData.getData().getList().get(position).getDepartment());
        viewHolder.tvFirstMoney.setText(getValueData.getData().getList().get(position).getFirst());
        viewHolder.tvSecondMoney.setText(getValueData.getData().getList().get(position).getSecond());
        viewHolder.tvThirdMoney.setText(getValueData.getData().getList().get(position).getThird());
        viewHolder.tvForthMoney.setText(getValueData.getData().getList().get(position).getFourth());
        viewHolder.tvAllMoney.setText(getValueData.getData().getList().get(position).getTotal());
        viewHolder.tvAlreadyMoney.setText(getValueData.getData().getList().get(position).getAllot());
        viewHolder.tvShouldMoney.setText(getValueData.getData().getList().get(position).getDistrubution());
        viewHolder.tvSurplusMoney.setText(getValueData.getData().getList().get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return getValueData.getData().getList().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_cb_vis)
        RelativeLayout layoutCbVis;
        @BindView(R.id.cb_select)
        public CheckBox cbSelect;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_Depart_name)
        TextView tvDepartName;
        @BindView(R.id.tv_first_money)
        TextView tvFirstMoney;
        @BindView(R.id.tv_second_money)
        TextView tvSecondMoney;
        @BindView(R.id.tv_third_money)
        TextView tvThirdMoney;
        @BindView(R.id.tv_forth_money)
        TextView tvForthMoney;
        @BindView(R.id.tv_all_money)
        TextView tvAllMoney;
        @BindView(R.id.tv_already_money)
        TextView tvAlreadyMoney;
        @BindView(R.id.tv_should_money)
        TextView tvShouldMoney;
        @BindView(R.id.tv_surplus_money)
        TextView tvSurplusMoney;
        @BindView(R.id.layout_title_type)
        LinearLayout layoutTitleType;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}