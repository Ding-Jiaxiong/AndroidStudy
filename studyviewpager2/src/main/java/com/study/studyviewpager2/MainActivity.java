package com.study.studyviewpager2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.study.studyviewpager2.adpter.PageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager2);

        List<String> dataList = new ArrayList<>();
        dataList.add("页面1");
        dataList.add("页面2");
        dataList.add("页面3");

        pageAdapter = new PageAdapter(dataList);
        viewPager2.setAdapter(pageAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText("标签" + (position + 1));
        }).attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, "选中第" + (position+1) + "页", Toast.LENGTH_SHORT).show();
            }
        });

    }
}