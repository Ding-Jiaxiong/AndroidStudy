package com.study.studybaseadapter.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.studybaseadapter.R;
import com.study.studybaseadapter.entity.GoodsEntity;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {

    private static final String TAG = "baseadapter";
    private Context mContext;
    private List<GoodsEntity> mData;
    private LayoutInflater mInflater;

    public GoodsAdapter(Context mContext, List<GoodsEntity> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_goods, parent, false);

            holder = new ViewHolder();
            holder.ivGoods = convertView.findViewById(R.id.iv_goods);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvPrice = convertView.findViewById(R.id.tv_price);
            holder.tvSales = convertView.findViewById(R.id.tv_sales);
            holder.ivCollect = convertView.findViewById(R.id.iv_collect);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GoodsEntity goods = mData.get(position);

        holder.ivGoods.setImageResource(goods.getImgRes());
        holder.tvTitle.setText(goods.getTitle());
        holder.tvPrice.setText(String.format("%.2f", goods.getPrice()));
        holder.tvSales.setText(goods.getSales() == null ? "" : goods.getSales().toString());
        holder.ivCollect.setImageResource(goods.isCollected() ? R.drawable.ic_collect_seleted : R.drawable.ic_collect_normal);

        holder.ivCollect.setOnClickListener(v -> {
            goods.setCollected(!goods.isCollected());
            notifyDataSetChanged();

//            for (GoodsEntity entity : mData) {
//                Log.d(TAG, entity.isCollected() + "");
//            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView ivGoods;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvSales;
        ImageView ivCollect;
    }
}
