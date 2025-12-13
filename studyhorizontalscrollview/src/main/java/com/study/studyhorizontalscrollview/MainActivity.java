package com.study.studyhorizontalscrollview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HorizontalScrollView";
    private HorizontalScrollView scrollView;

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.horizontalscrollview);
        container = findViewById(R.id.container);


        bindScrollButtons();
    }

    private void bindScrollButtons() {
        // 按钮1：滚动到最左侧
        Button btnToLeft = findViewById(R.id.btn_to_left);
        btnToLeft.setOnClickListener(v -> scrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT));

        // 按钮2：滚动到最右侧
        Button btnToRight = findViewById(R.id.btn_to_right);
        btnToRight.setOnClickListener(v -> scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT));

        // 按钮3：滚动到指定X坐标（如 300px，无动画）
        Button btnToX = findViewById(R.id.btn_to_x);
        btnToX.setOnClickListener(v -> scrollView.scrollTo(300, 0)); // scrollTo(x, y)，y固定为0（横向滚动）

        // 按钮4：平滑滚动到指定X坐标（如 600px，带动画）
        Button btnSmoothToX = findViewById(R.id.btn_smooth_to_x);
        btnSmoothToX.setOnClickListener(v -> scrollView.smoothScrollTo(600, 0));

        // 按钮5：滚动到第5个子项的位置（实战常用：定位到指定子View）
        Button btnToChild5 = findViewById(R.id.btn_to_child5);
        btnToChild5.setOnClickListener(v -> scrollToTargetChild(4)); // 索引从0开始，第5个子项索引为4
    }

    private void scrollToTargetChild(int position) {
        if (position < 0 || position >= container.getChildCount()) {
            Log.e(TAG, "scrollToTargetChild: 子项索引超出范围");
        }

        View targetView = container.getChildAt(position);
        int targetX = targetView.getLeft();

        scrollView.smoothScrollTo(targetX, 0);
    }
}