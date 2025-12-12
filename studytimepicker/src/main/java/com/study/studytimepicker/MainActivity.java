package com.study.studytimepicker;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private TextView textView;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnShowTimeDialog);
        textView = findViewById(R.id.tvTime);

        button.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    MainActivity.this,
                    (view, hourOfDay, minute) -> {
                        String timeText = String.format("选择的时间: %02d:%02d", hourOfDay, minute);
                        textView.setText(timeText);
                    },
                    currentHour,
                    currentMinute,
                    true
            );
//            timePicker.setIs24HourView(true);

            timePickerDialog.show();
        });

//        timePicker = findViewById(R.id.timePicker);
//        textView = findViewById(R.id.tvTime);

//        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
//                String timeText = String.format("选择的时间: %02d:%02d", hourOfDay, minute);
//                textView.setText(timeText);
//            }
//        });
    }
}