package com.men.calculateclient.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class MyTestIntentService extends IntentService {
    private boolean isRunning;
    private int count =0;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyTestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(1000);
            isRunning =true;
            while (isRunning) {
                count++;
                if (count > 100) {
                    isRunning = false;
                }
                Thread.sleep(50);
                sendThreadStatus("线程运行中",count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendThreadStatus(String statue, int count) {
        Notification notification = new Notification();
            startForeground(1,notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return super.onBind(intent);
    }
}
