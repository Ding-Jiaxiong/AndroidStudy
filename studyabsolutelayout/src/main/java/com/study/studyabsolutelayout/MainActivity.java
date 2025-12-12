package com.study.studyabsolutelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int offsetX = 0;
    private final int STEP = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_info);
        Button button = findViewById(R.id.button_move);

        int stepPx = dp2px(STEP);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentX = (int) textView.getX();
                offsetX += stepPx;
                int newX = currentX + offsetX;

                if (newX > getResources().getDisplayMetrics().widthPixels - 200) {
                    offsetX = 0;
                    newX = dp2px(50);
                }

                AbsoluteLayout.LayoutParams params = (AbsoluteLayout.LayoutParams) textView.getLayoutParams();
                params.x = newX;

                textView.setLayoutParams(params);
            }
        });

    }

    private int dp2px(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

}