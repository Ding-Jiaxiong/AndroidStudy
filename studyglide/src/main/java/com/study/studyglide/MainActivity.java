package com.study.studyglide;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.myimageview);

        Glide.with(this)
                .load("https://img1.baidu.com/it/u=1784288539,135206416&fm=253&app=120&f=GIF?w=420&h=360")
                .into(imageView);
    }
}