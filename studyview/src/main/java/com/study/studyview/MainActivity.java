package com.study.studyview;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setPadding(16, 16, 16, 16);

        TextView textView = new TextView(this);
        textView.setText("TextView from Code!");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        Button button = new Button(this);
        button.setText("Button from Code!");

        rootLayout.addView(textView);
        rootLayout.addView(button);

        setContentView(rootLayout);
    }
}