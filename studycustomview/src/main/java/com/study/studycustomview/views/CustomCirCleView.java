package com.study.studycustomview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class CustomCirCleView extends View {

    private Paint paint;

    public CustomCirCleView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        canvas.drawCircle(centerX, centerY, 500, paint);
    }
}
