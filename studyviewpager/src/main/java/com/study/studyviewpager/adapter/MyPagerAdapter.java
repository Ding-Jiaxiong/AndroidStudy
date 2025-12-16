package com.study.studyviewpager.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.study.studyviewpager.R;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {

    private List<View> mViewList;
    private List<String> mTitleList;

    public MyPagerAdapter(List<View> viewList, List<String> titleList) {
        this.mViewList = viewList;
        this.mTitleList = titleList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        TextView tvContent = mViewList.get(position).findViewById(R.id.tv_page_content);
        tvContent.setText("第" + (position + 1) + "页");
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
