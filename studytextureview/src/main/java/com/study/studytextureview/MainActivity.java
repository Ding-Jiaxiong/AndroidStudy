package com.study.studytextureview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    private TextureView mTextureView;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextureView = findViewById(R.id.simpleTextureView);
        mTextureView.setSurfaceTextureListener(this);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
        mHandler.post(mDrawRunnale);
        drawOnTextureView();
    }

    @Override
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
        drawOnTextureView();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
        mHandler.removeCallbacks(mDrawRunnale);
        surfaceTexture.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

    }

    private int rectX = 100;
    private Handler mHandler = new Handler();
    private Runnable mDrawRunnale = new Runnable() {
        @Override
        public void run() {
            rectX += 5;
            if (rectX > mTextureView.getWidth()) {
                rectX = 100;
            }

            drawOnTextureView();
            mHandler.postDelayed(this, 30);
        }
    };

    private void drawOnTextureView() {

        Canvas canvas = mTextureView.lockCanvas();

        if (canvas == null) {
            return;
        }

        try {
            canvas.drawColor(Color.BLACK);

            mPaint.setColor(Color.RED);
            canvas.drawRect(rectX, 200, rectX + 700, 600, mPaint);

            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(60);
            canvas.drawText("TextureView 极简示例", 150, 400, mPaint);
        } finally {
            mTextureView.unlockCanvasAndPost(canvas);
        }

    }

}