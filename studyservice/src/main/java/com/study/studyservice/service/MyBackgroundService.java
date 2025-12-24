package com.study.studyservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyBackgroundService extends Service {

    private static final String TAG = "MyBackgroundService";
    private boolean isRunning = false;

    public MyBackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Service 被创建了");
        isRunning = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                        count++;
                        Log.d(TAG, "run: 后台任务运行中，计数: " + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Service 被启动了");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service 被销毁了");
        isRunning = false;
    }
}