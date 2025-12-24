package com.study.studyservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.study.studyservice.R;
import com.study.studyservice.service.MyBackgroundService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.btn_start_service);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, MyBackgroundService.class);
                startService(startIntent);
            }
        });

        Button stop = findViewById(R.id.btn_stop_service);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopInten = new Intent(MainActivity.this, MyBackgroundService.class);
                stopService(stopInten);
            }
        });
    }
}