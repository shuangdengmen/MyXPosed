package com.men.hhapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyTestReceive extends BroadcastReceiver {
    Activity activity;

    public MyTestReceive(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.finish();
        Log.d("", "onReceive: "+activity.getClass().getName());

    }
}
