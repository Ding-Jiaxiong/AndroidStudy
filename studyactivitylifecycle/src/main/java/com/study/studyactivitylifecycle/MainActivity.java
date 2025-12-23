package com.study.studyactivitylifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Activity 生命周期";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "执行了 onCreate()");

        Button button = findViewById(R.id.btn_finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "执行了 onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "执行了 onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "执行了 onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "执行了 onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "执行了 onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "执行了 onDestroy()");
    }


}