package com.study.studybaseadapter;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.study.studybaseadapter.adapter.GoodsAdapter;
import com.study.studybaseadapter.entity.GoodsEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvGoods;
    private GoodsAdapter adapter;
    private List<GoodsEntity> goodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvGoods = findViewById(R.id.lv_goods);

        goodsList = new ArrayList<>();
        goodsList.add(new GoodsEntity(R.drawable.goods1, "小米14 旗舰手机", 3999, 111231, false));
        goodsList.add(new GoodsEntity(R.drawable.goods2, "华为Mate 60 Pro", 6999, 20, true));
        goodsList.add(new GoodsEntity(R.drawable.goods3, "苹果iPhone 15", 5999, 15, false));

        adapter = new GoodsAdapter(
                this, goodsList
        );
        lvGoods.setAdapter(adapter);

        // 4. 处理 Item 点击事件
        lvGoods.setOnItemClickListener((parent, view, position, id) -> {
            GoodsEntity goods = (GoodsEntity) adapter.getItem(position);
            Toast.makeText(this, "点击了：" + goods.getTitle(), Toast.LENGTH_SHORT).show();
        });

        // 5. 可选：处理 Item 长按事件
        lvGoods.setOnItemLongClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "长按了：" + goodsList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}