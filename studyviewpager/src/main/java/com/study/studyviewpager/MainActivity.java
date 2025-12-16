package com.study.studyviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.study.studyviewpager.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyPagerAdapter adapter;

    private List<View> mViewList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);

        LayoutInflater inflater = LayoutInflater.from(this);

        View page1 = inflater.inflate(R.layout.page_item, null);
        View page2 = inflater.inflate(R.layout.page_item, null);
        View page3 = inflater.inflate(R.layout.page_item, null);

        mViewList.add(page1);
        mViewList.add(page2);
        mViewList.add(page3);

        mTitleList.add("页面1");
        mTitleList.add("页面2");
        mTitleList.add("页面3");

        adapter = new MyPagerAdapter(mViewList, mTitleList);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, "选中第" + (position+1) + "页", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}