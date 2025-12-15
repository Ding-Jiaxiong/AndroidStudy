package com.study.studyrecyclerviewadapter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.studyrecyclerviewadapter.R;
import com.study.studyrecyclerviewadapter.entity.NewsEntity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsEntity> newsList;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(List<NewsEntity> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsEntity news = newsList.get(position);
        holder.ivCover.setImageResource(news.getCoverRes());
        holder.tvTitle.setText(news.getTitle());
        holder.tvPublishTime.setText(news.getPublishTime());
        holder.tvReadCount.setText("阅读量: " + news.getReadCount());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCover;
        TextView tvTitle;
        TextView tvPublishTime;
        TextView tvReadCount;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPublishTime = itemView.findViewById(R.id.tv_publish_time);
            tvReadCount = itemView.findViewById(R.id.tv_read_count);
        }
    }

    // 添加数据
    public void addNews(NewsEntity news) {
        int position = newsList.size();
        newsList.add(news);
        notifyItemInserted(position);
    }

    // 删除数据
    public void removeNews(int position) {
        if (position >= 0 && position < newsList.size()) {
            newsList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, newsList.size());
        }
    }

    // ===================== 点击事件接口 =====================
    public interface OnItemClickListener {
        void onItemClick(NewsEntity news);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
