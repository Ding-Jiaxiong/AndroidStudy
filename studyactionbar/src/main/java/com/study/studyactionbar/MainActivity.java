package com.study.studyactionbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "点击了搜索", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_share) {
            Toast.makeText(this, "点击了分享", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_settings) {
            Toast.makeText(this, "点击了设置", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == android.R.id.home) { // 系统返回按钮ID
            Toast.makeText(this, "点击了返回", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("我的 ActionBar ");
//        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}