package com.study.studycheckbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox apple;
    private CheckBox banana;
    private CheckBox orange;
    private Button mybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apple = findViewById(R.id.checkbox_apple);
        banana = findViewById(R.id.checkbox_banana);
        orange = findViewById(R.id.checkbox_orange);
        mybutton = findViewById(R.id.mybutton);

        apple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String msg = isChecked ? "选中了苹果" : "取消选中苹果";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        banana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String msg = isChecked ? "选中了香蕉" : "取消选中香蕉";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedFruits();
            }
        });

    }

    private void getSelectedFruits() {
        StringBuilder res = new StringBuilder("选中的水果: ");

        if (apple.isChecked()) {
            res.append("苹果 ");
        }
        if (banana.isChecked()) {
            res.append("香蕉 ");
        }
        if (res.toString().equals("选中的水果: ")) {
            res.append("无");
        }

        Toast.makeText(MainActivity.this, res.toString(), Toast.LENGTH_LONG).show();
    }
}