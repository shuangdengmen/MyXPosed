package com.men.calculateclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.men.calculate.CalculateInterface;
import com.men.calculateclient.activity.BroadcastActivity;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private CalculateInterface calculateInterface;
    private EditText edA;
    private EditText edB;
    private double mResult;
    private TextView tvResulst;
    private boolean mBound;
    private ImageView myPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long id = -1;
        if (savedInstanceState != null) {
             id = savedInstanceState.getLong("id");
        }
        Log.d("id", "onCreate: id"+id);
        edA = findViewById(R.id.et_a);
        edB = findViewById(R.id.et_b);
        myPic = findViewById(R.id.iv_mypic);
        tvResulst = findViewById(R.id.tv_result);
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                calculateInterface = CalculateInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent();
        intent.setPackage("com.men.hhapplication");
        intent.setAction("com.men.hhapplication.service.CalculateService");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        Executor executor = Executors.newFixedThreadPool(3);
        new AsyncTask<Void,Void,Uri>() {
            @Override
            protected Uri doInBackground(Void... values) {
                String filedir = Environment.getExternalStorageDirectory()+File.separator+"mypic"+File.separator;
                File file = new File(filedir + "111.jpg");
                Uri uri = Uri.fromFile(file);
                return uri;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Uri uri) {
                myPic.setImageURI(uri);
            }


        }.executeOnExecutor(executor);
    }

    public void onClick(View view) {

        if (view.getId()==R.id.btn_start){
            calcuteResult();
        } else if (view.getId() == R.id.btn_clickOrderCast) {
            Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
            startActivity(intent);
        }

    }

    private void calcuteResult() {
        Log.d("mResult", "onServiceConnected: result"+mResult);
        float  edAValue=Float.valueOf(edA.getText().toString());
        float  edBValue=Float.valueOf(edA.getText().toString());
        try {
            mResult = calculateInterface.doCalculate(edAValue, edBValue);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tvResulst.setText(mResult+"");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putLong("id",123456);
        super.onSaveInstanceState(outState, outPersistentState);
//        outState.putBundle();
    }

    public void onImage(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.View");
        Uri uri = Uri.parse("http://www.baidu.com");
        intent.setData(uri);
        intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
        startActivity(intent);

        Intent intent1 = new Intent();
        intent1.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
    }
}
