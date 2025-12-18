package com.study.studysearchview;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = findViewById(R.id.searchView);
        TextView tvResult = findViewById(R.id.tvResult);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                tvResult.setText("你提交了搜索: " + s);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty()) {
                    tvResult.setText("请输入搜索内容");
                } else {
                    tvResult.setText("实时搜索: "+ s);
                }
                return false;
            }
        });

        searchView.setOnCloseListener(() -> {
            tvResult.setText("搜索框已关闭");
            return false;
        });
    }
}