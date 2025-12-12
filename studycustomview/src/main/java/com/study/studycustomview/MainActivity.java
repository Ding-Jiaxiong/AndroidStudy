package com.study.studycustomview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.study.studycustomview.views.CustomCirCleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        CustomCirCleView cirCleView = new CustomCirCleView(this);
        setContentView(cirCleView);
    }
}