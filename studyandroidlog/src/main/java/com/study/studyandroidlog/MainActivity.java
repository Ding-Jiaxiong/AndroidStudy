package com.study.studyandroidlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tag", "我是一条日志信息");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}