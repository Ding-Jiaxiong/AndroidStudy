package com.study.studysurfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private DrawThread mDrawThread;

    // 小球属性（拆分X/Y方向速度，避免共用）
    private int ballX = 100;
    private int ballY = 100;
    private final int ballRadius = 50;
    private int speedX = 5;  // X方向速度
    private int speedY = 5;  // Y方向速度

    // 屏幕可用区域（避免重复计算）
    private int surfaceWidth;
    private int surfaceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = findViewById(R.id.surfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceView.setZOrderOnTop(true);
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);

        // 监听SurfaceView尺寸变化（解决边界判断问题）
        mSurfaceView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                surfaceWidth = right - left;
                surfaceHeight = bottom - top;
                // 确保小球初始位置在边界内
                ballX = Math.max(ballRadius, Math.min(ballX, surfaceWidth - ballRadius));
                ballY = Math.max(ballRadius, Math.min(ballY, surfaceHeight - ballRadius));
            }
        });
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        // 启动绘制线程（双重校验避免重复创建）
        if (mDrawThread == null || !mDrawThread.isAlive()) {
            mDrawThread = new DrawThread();
            mDrawThread.isRunning = true;
            mDrawThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        // 更新Surface尺寸
        surfaceWidth = width;
        surfaceHeight = height;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        // 安全终止线程
        if (mDrawThread != null) {
            mDrawThread.isRunning = false;
            // 中断线程（避免sleep阻塞）
            mDrawThread.interrupt();
            try {
                // 等待线程终止（超时保护）
                mDrawThread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mDrawThread = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        surfaceDestroyed(mSurfaceHolder); // 确保线程终止
    }

    /**
     * 修复后的绘制线程
     * 1. 拆分X/Y速度
     * 2. 优化帧率控制
     * 3. 安全的画布锁操作
     * 4. 正确的边界碰撞判断
     */
    private class DrawThread extends Thread {
        volatile boolean isRunning = false; // 加volatile保证线程可见性
        private final Paint paint;
        private long lastFrameTime; // 上一帧时间（控制帧率）

        public DrawThread() {
            // 初始化画笔（避免重复创建）
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            lastFrameTime = System.currentTimeMillis();
        }

        @Override
        public void run() {
            super.run();
            // 目标帧率：60fps（约16ms/帧）
            final long FRAME_INTERVAL = 16;

            while (isRunning) {
                Canvas canvas = null;
                try {
                    // 计算帧间隔，控制帧率
                    long currentTime = System.currentTimeMillis();
                    long deltaTime = currentTime - lastFrameTime;
                    if (deltaTime < FRAME_INTERVAL) {
                        Thread.sleep(FRAME_INTERVAL - deltaTime);
                    }
                    lastFrameTime = System.currentTimeMillis();

                    // 安全锁画布（加同步锁避免并发问题）
                    synchronized (mSurfaceHolder) {
                        canvas = mSurfaceHolder.lockCanvas();
                    }

                    if (canvas == null || surfaceWidth == 0 || surfaceHeight == 0) {
                        continue;
                    }

                    // 1. 清空画布
                    canvas.drawColor(Color.WHITE);

                    // 2. 更新小球位置
                    ballX += speedX;
                    ballY += speedY;

                    // 3. 边界碰撞检测（修正反弹逻辑）
                    // X方向反弹
                    if (ballX - ballRadius <= 0) {
                        ballX = ballRadius; // 避免越界
                        speedX = Math.abs(speedX); // 向右
                    } else if (ballX + ballRadius >= surfaceWidth) {
                        ballX = surfaceWidth - ballRadius; // 避免越界
                        speedX = -Math.abs(speedX); // 向左
                    }

                    // Y方向反弹
                    if (ballY - ballRadius <= 0) {
                        ballY = ballRadius; // 避免越界
                        speedY = Math.abs(speedY); // 向下
                    } else if (ballY + ballRadius >= surfaceHeight) {
                        ballY = surfaceHeight - ballRadius; // 避免越界
                        speedY = -Math.abs(speedY); // 向上
                    }

                    // 4. 绘制小球
                    canvas.drawCircle(ballX, ballY, ballRadius, paint);

                } catch (InterruptedException e) {
                    // 线程被中断，正常退出
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 安全解锁画布（必须在finally中执行）
                    if (canvas != null) {
                        try {
                            synchronized (mSurfaceHolder) {
                                mSurfaceHolder.unlockCanvasAndPost(canvas);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}