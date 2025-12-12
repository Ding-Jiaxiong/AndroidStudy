package com.study.studyradiobutton;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.myradiogroup);
        textView = findViewById(R.id.mytv);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                String fruit = "";
                if (checkedId == R.id.radiobutton1) {
                    fruit = "香蕉";
                } else if (checkedId == R.id.radiobutton2) {
                    fruit = "苹果";
                } else if (checkedId == R.id.radiobutton3) {
                    fruit = "草莓";
                }
                textView.setText("你选择的水果是: " + fruit);
            }
        });
    }
}