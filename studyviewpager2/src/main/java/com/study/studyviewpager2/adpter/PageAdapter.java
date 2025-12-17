package com.study.studyviewpager2.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.studyviewpager2.R;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder> {

    private List<String> mDataList;

    public PageAdapter(List<String> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public PageAdapter.PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_page, parent, false);
        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageAdapter.PageViewHolder holder, int position) {
        holder.tvContent.setText(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    static class PageViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
