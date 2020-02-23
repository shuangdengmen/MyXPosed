package com.men.calculateclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.men.calculateclient.R;
import com.men.calculateclient.receiver.Priority2BroadcastReceiver;

public class BroadcastActivity extends Activity {
private static final String ACTION = "com.tbc.hsx.testbroadcast.MainActivity";
public static final String DATA = "data";
public static final String ORDER_DATA = "order_data";

        Handler handler = new Handler() {
@Override
public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        }
        };
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        }



public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra(DATA, "有序广播通过intent.putExtra传递的数据");
        intent.setAction(ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(ORDER_DATA, "有序广播通过bundle传递的数据");

        sendOrderedBroadcast(intent, null, new Priority2BroadcastReceiver(), handler, Activity.RESULT_OK, "MainActivity发送了一个有序广播", bundle);
        }
        }
