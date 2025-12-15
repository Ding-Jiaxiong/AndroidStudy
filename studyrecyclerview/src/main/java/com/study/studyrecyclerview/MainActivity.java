package com.study.studyrecyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.study.studyrecyclerview.adpter.TextAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextAdapter adapter;
    private List<String> dataList;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();

        dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            dataList.add("列表项:" + i);
        }

        recyclerView = findViewById(R.id.recyclerview);

        // 1. 创建瀑布流布局管理器
        // 参数1：列数（2列）；参数2：滚动方向（VERTICAL 垂直/ HORIZONTAL 水平）
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);

//        // 可选：解决瀑布流滑动时item错位问题
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TextAdapter(this, dataList);
        recyclerView.setAdapter(adapter);

        // 改动：设置Item点击监听（核心新增）
        adapter.setOnItemClickListener((position, content) -> {
            // 点击后的逻辑，示例：弹Toast
            Toast.makeText(MainActivity.this,
                    "点击了：" + content + "（位置：" + position + "）",
                    Toast.LENGTH_SHORT).show();
        });

        // 改动2：添加滑动监听，解决瀑布流错位问题
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
    }

    public int getRandomHeight() {
        return random.nextInt(500) + 300;
    }
}