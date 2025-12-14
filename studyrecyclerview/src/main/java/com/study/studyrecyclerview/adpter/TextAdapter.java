package com.study.studyrecyclerview.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.study.studyrecyclerview.MainActivity;
import com.study.studyrecyclerview.R;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {

    private List<String> mDataList;

    // 改动1：新增上下文变量，接收MainActivity
    private MainActivity mContext;

    // 新增：点击事件回调接口
    private OnItemClickListener mOnItemClickListener;

    // 改动2：修改构造方法，传入MainActivity和数据
    public TextAdapter(MainActivity context, List<String> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 新增：点击回调接口定义
    public interface OnItemClickListener {
        void onItemClick(int position, String content);
    }

    @NonNull
    @Override
    public TextAdapter.TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text, parent, false);

        return new TextViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TextAdapter.TextViewHolder holder, int position) {
        String text = mDataList.get(position);
        holder.tvItem.setText(text);

        // 改动3：设置Item随机高度（瀑布流核心效果）
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mContext.getRandomHeight();
        holder.itemView.setLayoutParams(layoutParams);

        // 改动2：给Item设置点击事件
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(position, text);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }
}
