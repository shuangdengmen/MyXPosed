package com.men.hhapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.men.hhapplication.db.MySqliteHelper;
import com.men.hhapplication.provider.MyTestProvider;
import com.men.hhapplication.resolver.MyTestResolver;

import java.io.File;
import java.util.List;

public class Splash2Activity extends Activity {

    private MyTestReceive myTestReceive;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            closeBackgroundActivity();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        myTestReceive = new MyTestReceive(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.men.hhapplication.finish");
        registerReceiver(myTestReceive, intentFilter);
        closeBackgroundActivity();
//        insertData();
    }

    private void insertData() {
        SQLiteDatabase readableDatabase = new MySqliteHelper(getApplicationContext()).getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_people_id",1);
        contentValues.put("people", "black");
         contentValues.put("calls",30);
        readableDatabase.insert("mytest", null, contentValues);
    }

    public void onFinish(View view) {
//        queryMyResolver();
        test();
    }

    public void test() {

        String filedir = Environment.getExternalStorageDirectory() + File.separator + "mypic";
        Log.d("Filedir", "test:"+filedir);
        String filepath = filedir+File.separator + "111.jpg";
        File file = new File(filepath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Splash2Activity.this,Splash3Activity.class);
        intent.setData(uri);

        startActivity(intent);
    }
    private void queryMyResolver() {
        int  query_id =-1;
        String querystr="";
        int calls =0;
//        closeActivity();
//        MyTestResolver resolver = new MyTestResolver(getApplicationContext());
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.men.hhapplication.provider.mytest/query");
//        String result = resolver.getResult();
        Cursor query = contentResolver.query(uri, new String[]{"_people_id","people","calls"}, null,null,null);
        if (query != null) {
            while (query.moveToNext()) {
              query_id = query.getInt(0);
               querystr = query.getString(1);
                calls = query.getInt(2);

            }
        }
        query.close();
        Log.d("resolver", "resolver: result:"+query_id+","+querystr+","+calls);
    }

    private void closeActivity() {
        Intent intent = new Intent();
        intent.setAction("com.men.hhapplication.finish");
        this.sendBroadcast(intent);
    }

    private  void closeBackgroundActivity() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(20);
        for (int i = 0; i < runningTasks.size(); i++) {
            ActivityManager.RunningTaskInfo taskInfo = runningTasks.get(i);
            if (taskInfo.topActivity.getPackageName().equals(getPackageName())) {
                Log.d("closeBackgroundActivity", "closeBackgroundActivity: 前台:" + getPackageName());
                break;
            } else {
                Log.d("closeBackgroundActivity", "closeBackgroundActivity: 后台:" + getPackageName());
                break;
            }

        }

        handler.sendEmptyMessageDelayed(0,3000);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myTestReceive);
        Log.d("", "SplashActivity2...finish ");
        super.onDestroy();
    }
}
