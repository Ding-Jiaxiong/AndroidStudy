package com.study.studyrecyclerviewadapter;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.studyrecyclerviewadapter.adapter.NewsAdapter;
import com.study.studyrecyclerviewadapter.entity.NewsEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNews;
    private NewsAdapter adapter;
    private List<NewsEntity> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNews = findViewById(R.id.rv_news);

        rvNews.setLayoutManager(new LinearLayoutManager(this));

        initTestData();

        adapter = new NewsAdapter(newsList);
        rvNews.setAdapter(adapter);

        adapter.setOnItemClickListener(news -> {
            Toast.makeText(MainActivity.this, "点击了: " + news.getTitle(), Toast.LENGTH_SHORT).show();

        });

        findViewById(R.id.btn_add).setOnClickListener(v -> {
            NewsEntity newNews = new NewsEntity(
                    System.currentTimeMillis(),
                    "新发布的热点新闻",
                    R.drawable.cover4,
                    "2025-12-15 22:16",
                    0
            );
            adapter.addNews(newNews);
        });

        findViewById(R.id.btn_delete).setOnClickListener(v -> adapter.removeNews(0));
    }

    private void initTestData() {

        newsList.add(new NewsEntity(1, "Android 15 正式发布", R.drawable.cover1, "2025-01-01 08:00", 10000));
        newsList.add(new NewsEntity(2, "RecyclerView 性能优化指南", R.drawable.cover2, "2025-01-01 09:00", 5000));
        newsList.add(new NewsEntity(3, "Kotlin 1.9 新特性解析", R.drawable.cover3, "2025-01-01 10:00", 8000));

    }
}