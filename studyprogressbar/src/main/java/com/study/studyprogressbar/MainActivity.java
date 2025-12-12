package com.study.studyprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ProgressBar";
    // 声明控件
    private ProgressBar loadingProgressBar;
    private ProgressBar horizontalProgressBar;
    private TextView tvProgress;
    private Handler mainHandler;
    private int currentProgress = 0;  // 当前进度值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingProgressBar = findViewById(R.id.progressbar_loading);
        horizontalProgressBar = findViewById(R.id.progressbar_horizontal);
        tvProgress = findViewById(R.id.mytv);

        mainHandler = new Handler(Looper.getMainLooper());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 关键修改1：循环条件改为 < 100，避免最后一次进入循环加5
                    while (currentProgress < 100) {
                        Thread.sleep(500);
                        // 关键修改2：限制进度不超过100
                        currentProgress = Math.min(currentProgress + 5, 100);

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                horizontalProgressBar.setProgress(currentProgress);
                                String current;
                                // 显示逻辑调整
                                if (currentProgress == 100) {
                                    current = "加载完成!";
                                    loadingProgressBar.setIndeterminate(false);
                                    loadingProgressBar.setVisibility(View.GONE);
                                } else {
                                    current = "当前进度: " + currentProgress + "%";
                                }
                                Log.d(TAG, current);
                                tvProgress.setText(current);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}