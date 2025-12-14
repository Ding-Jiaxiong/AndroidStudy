package com.study.studygridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<Map<String, Object>> dataList;

    private int[] icons = {
            R.drawable.one, R.drawable.two, R.drawable.three,
            R.drawable.four, R.drawable.five, R.drawable.six,
            R.drawable.seven, R.drawable.eight, R.drawable.nine
    };

    private String[] names = {
            "应用1", "应用2", "应用3",
            "应用4", "应用5", "应用6",
            "应用7", "应用8", "应用9"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        dataList = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("icon", icons[i]);
            map.put("name", names[i]);
            dataList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                dataList,
                R.layout.item_grid,
                new String[]{"icon", "name"},
                new int[]{R.id.iv_icon, R.id.tv_name}
        );

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = names[position];
                Toast.makeText(MainActivity.this, "点击了: " + name, Toast.LENGTH_SHORT).show();
            }
        });

    }
}