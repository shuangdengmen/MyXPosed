package com.men.hhapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.men.hhapplication.service.MyTestService;

public class Splash3Activity extends FragmentActivity {

    private Messenger messenger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);
        setTitle("你好标题");
         ImageView ivMypic = (ImageView) findViewById(R.id.iv_mypic);
        Uri data = getIntent().getData();
        ivMypic.setImageURI(data);
        Intent intent = new Intent(Splash3Activity.this, MyTestService.class);
        intent.putExtra("id", 123456);
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                messenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        startService(intent);
//        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);

    }

    public void onClick() {
        Message message = Message.obtain();
        message.what = 5;
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);
    }
}
