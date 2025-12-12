package com.study.studysimpleadpter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv_simple);

        List<Map<String, Object>> data = new ArrayList<>();

        // Item 1
        Map<String, Object> item1 = new HashMap<>();
        item1.put("icon", R.drawable.ic_home); // 图标资源 ID
        item1.put("title", "首页");
        item1.put("desc", "推荐内容");
        data.add(item1);

        // Item 2
        Map<String, Object> item2 = new HashMap<>();
        item2.put("icon", R.drawable.ic_category);
        item2.put("title", "分类");
        item2.put("desc", "全部分类");
        data.add(item2);


        // Item 3
        Map<String, Object> item3 = new HashMap<>();
        item3.put("icon", R.drawable.ic_mine);
        item3.put("title", "我的");
        item3.put("desc", "个人中心");
        data.add(item3);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.item_simple,
                new String[]{"icon", "title", "desc"},
                new int[]{R.id.iv_icon, R.id.tv_title, R.id.tv_desc}
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Map<String, Object> item = data.get(position);
                String title = (String) item.get("title");

                Toast.makeText(MainActivity.this, "点击了 " + title, Toast.LENGTH_SHORT).show();
            }
        });

    }
}