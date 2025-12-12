package com.study.studyframelayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

//    private FrameLayout frameLayout;
//    private Button button;
//
//    private int[] colors = {0xFFFF5722, 0xFF4CAF50, 0xFFFFEB3B, 0xFF9C27B0, 0xFF00BCD4};
//
//    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.test_framelayout);

//        frameLayout = findViewById(R.id.frame_layout);
//        button = findViewById(R.id.add_view_button);
//
//        random = new Random();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // 创建新 View
//                TextView textView = new TextView(MainActivity.this);
//                textView.setText("新View: " + (frameLayout.getChildCount() - 3));
//                textView.setTextColor(0xFFFFFFFF);
//                textView.setTextSize(18);
//                textView.setPadding(20, 20, 20, 20);
//
//                // 设置随机颜色
//                int randomColor = colors[random.nextInt(colors.length)];
//                textView.setBackgroundColor(randomColor);
//
//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT
//                );
//
//                int[] gravityArr = {
//                        Gravity.LEFT | Gravity.TOP,
//                        Gravity.RIGHT | Gravity.TOP,
//                        Gravity.LEFT | Gravity.CENTER_VERTICAL,
//                        Gravity.RIGHT | Gravity.BOTTOM
//                };
//
//                params.gravity = gravityArr[random.nextInt(gravityArr.length)];
//
//                // 添加到我们的FrameLayout
//                frameLayout.addView(textView, params);
//            }
//        });

    }
}