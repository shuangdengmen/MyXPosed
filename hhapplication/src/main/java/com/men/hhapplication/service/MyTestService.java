package com.men.hhapplication.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyTestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("service", "onBind: ");
        intent.putExtra("num", 1);
        Binder binder = new Binder();
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = new Notification();
        notification.flags=Notification.FLAG_ONGOING_EVENT;
        notification.flags|=Notification.FLAG_NO_CLEAR;
        notification.flags|=Notification.FLAG_FOREGROUND_SERVICE;
        this.startForeground(1,notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "onStartCommand...intent.getid: "+intent.getIntExtra("id",-1));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.d("service", "onDestroy.. ");
    }
}
