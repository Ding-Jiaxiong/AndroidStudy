package com.study.studydatepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView textView;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnshowDatePicker);
        textView = findViewById(R.id.tvDate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                int realMonth = month + 1;
                                textView.setText("选中: " + year + "年" + realMonth + "月" + dayOfMonth + "日");
                            }
                        },
                        year, month, day
                );

                datePickerDialog.show();
            }
        });

//        datePicker = findViewById(R.id.datepicker);
//        textView = findViewById(R.id.tvDate);
//
//        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
//                StringBuilder builder = new StringBuilder();
//                builder.append(year).append("年").append(month + 1).append("月").append(dayOfMonth).append("日");
//                textView.setText(builder.toString());
//            }
//        });
    }
}