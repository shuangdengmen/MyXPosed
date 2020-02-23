package com.men.hhapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.men.calculate.CalculateInterface;

public class CalculateService extends Service {
    private static final String TAG = "CalculateService";

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        logE("onBind()");
        return mBinder;
//        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        logE("onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        logE("onStart()");
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        logE("onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        logE("onDestroy()");
        super.onDestroy();
    }

    private static void logE(String str) {
        Log.e(TAG, "--------" + str + "--------");
    }

    private final CalculateInterface.Stub mBinder = new CalculateInterface.Stub() {

        @Override
        public double doCalculate(double a, double b) {
            // TODO Auto-generated method stub
            Log.e("Calculate", "远程计算中");
            return a;
        }
    };


}
