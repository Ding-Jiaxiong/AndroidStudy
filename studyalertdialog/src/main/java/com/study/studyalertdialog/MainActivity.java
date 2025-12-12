package com.study.studyalertdialog;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "对话框";
    private Button button;

    private int selectedGenderIndex = 0;

    private void showSimpleAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("提示")
                .setMessage("简单的提示弹窗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(MainActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(MainActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

//        AlertDialog dialog = builder.create();
//        dialog.show();
        builder.show();
    }

    private void showListAlertDialog() {
        String[] items = {"选项1", "选项2", "选项3", "选项4"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个选项")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "你选择了: " + items[i], Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

        builder.show();
    }


    private void showSingleChoiceAlertDialog() {
        String[] items = {"男", "女", "保密"};
        int defaultSelect = 0;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择性别")
                .setSingleChoiceItems(items, defaultSelect, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: 选择了" + items[i]);
                        selectedGenderIndex = i;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "你选择的性别是: " + items[selectedGenderIndex], Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

        builder.show();
    }

    private void showCustomLayoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义对话框");

        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.dialog_custom, null);
        builder.setView(customView);

        EditText editText = customView.findViewById(R.id.et_input);
        Button dialogButton = customView.findViewById(R.id.btn_submit);
        AlertDialog dialog = builder.create();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputContent = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(inputContent)) {
                    Toast.makeText(MainActivity.this, "你输入的是：" + inputContent, Toast.LENGTH_SHORT).show();
                    dialog.dismiss(); // 关闭弹窗
                } else {
                    Toast.makeText(MainActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_showdialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 按钮被点击了!");
//                showSimpleAlertDialog();
//                showListAlertDialog();
//                showSingleChoiceAlertDialog();
                showCustomLayoutDialog();
            }
        });
    }
}