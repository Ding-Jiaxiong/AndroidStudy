package com.study.studyedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "我的EditText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.myedittext);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "文本开始变化");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentText = s.toString().trim();
                Log.d("实时输入", currentText);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "文本已经变化完毕");
            }
        });
    }
}