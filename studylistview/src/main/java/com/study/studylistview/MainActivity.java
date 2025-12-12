package com.study.studylistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        List<String> dataList = new ArrayList<>();
        dataList.add("条目1: Android 基础");
        dataList.add("条目2: ListView 基础");
        dataList.add("条目3: Adapter 基础");
        dataList.add("条目4: 自定义 Adapter");
        dataList.add("条目5: 列表项点击事件");
        dataList.add("条目6: 列表项长按事件");
        dataList.add("条目7: 列表项选中事件");
        dataList.add("条目1: Android 基础");
        dataList.add("条目2: ListView 基础");
        dataList.add("条目3: Adapter 基础");
        dataList.add("条目4: 自定义 Adapter");
        dataList.add("条目5: 列表项点击事件");
        dataList.add("条目6: 列表项长按事件");
        dataList.add("条目7: 列表项选中事件");
        dataList.add("条目1: Android 基础");
        dataList.add("条目2: ListView 基础");
        dataList.add("条目3: Adapter 基础");
        dataList.add("条目4: 自定义 Adapter");
        dataList.add("条目5: 列表项点击事件");
        dataList.add("条目6: 列表项长按事件");
        dataList.add("条目7: 列表项选中事件");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                dataList
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = dataList.get(i);
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });
    }
}