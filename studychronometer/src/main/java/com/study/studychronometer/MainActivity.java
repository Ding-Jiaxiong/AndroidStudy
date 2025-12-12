package com.study.studychronometer;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;

    private long pauseOffSet = 0;

    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);

        findViewById(R.id.btn_start).setOnClickListener(v -> startChronometer());
        findViewById(R.id.btn_stop).setOnClickListener(v -> stopChronometer());
        findViewById(R.id.btn_reset).setOnClickListener(v -> resetChronometer());

        chronometer.setOnChronometerTickListener(chronometer1 -> {
            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            if (elapsedMillis >= 10000) {
                chronometer.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        });
    }

    private void resetChronometer() {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffSet = 0;
        isRunning = false;
        chronometer.setTextColor(getResources().getColor(android.R.color.black));
    }

    private void stopChronometer() {
        if (isRunning) {
            chronometer.stop();
            pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
            isRunning = false;
        }
    }

    // 开始计时
    private void startChronometer() {
        if (!isRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
            chronometer.start();
            isRunning = true;
            chronometer.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
}