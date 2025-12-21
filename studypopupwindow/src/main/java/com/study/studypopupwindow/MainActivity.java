package com.study.studypopupwindow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowPopup = findViewById(R.id.btn_show_popup);
        btnShowPopup.setOnClickListener(v -> showPopupWindow(v));
    }

    private void showPopupWindow(View anchorView) {

        View popupView = LayoutInflater.from(this)
                .inflate(R.layout.popup_window_layout, null);

        mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));

        Button btnConfirm = popupView.findViewById(R.id.btn_popup_confirm);
        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "点击了确认", Toast.LENGTH_SHORT).show();
            mPopupWindow.dismiss(); // 关闭弹窗
        });

        mPopupWindow.showAsDropDown(
                anchorView,
                0,
                0
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }
}