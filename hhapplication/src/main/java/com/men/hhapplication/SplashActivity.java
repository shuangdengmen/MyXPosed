package com.men.hhapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.men.hhapplication.service.CalculateService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private TextView textView;
    private TextView ganraoTextView;
    private MyTestReceive myTestReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = (TextView) findViewById(R.id.tv_text);
        ganraoTextView = (TextView) findViewById(R.id.tv_ganrao);
        preferences = getSharedPreferences("save_login_first", MODE_PRIVATE);
        String firstLoginStr = "first";
        String secondStr = "second";
        saveFirstLoginState(firstLoginStr);
        saveFirstLoginState(secondStr);
        SplashActivity.GameTypeBean gameTypeBean= new SplashActivity.GameTypeBean();

        textView.setText("getType+--->:"+    gameTypeBean.getType()+"");

        ganraoTextView.setText(getSecondLoginState());

//        textView.setText(getResult());
//        openMyWeb();
        getFirstLoginState();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.men.hhapplication.finish");
        myTestReceive = new MyTestReceive(this);
        registerReceiver(myTestReceive, intentFilter);

        //启动远程服务
        Intent intent = new Intent(SplashActivity.this,CalculateService.class);
        startService(intent);

    }

    public void onClick(View view) {
        Intent intent = new Intent(SplashActivity.this, Splash2Activity.class);
        startActivity(intent);
    }


    public  class GameTypeBean{
        private int role=0;
        public  int getType() {
            return role;
        }
    }

    public String result() {
        return "cs";
    }

    private void saveFirstLoginState(String loginString) {

        if (loginString.equals("first")) {
            preferences.edit().putString("firstLoginState", loginString).apply();
        } else {
            preferences.edit().putString("ganraoState", loginString).apply();
        }
    }
    private void saveGanraoState(String loginString) {
        preferences.edit().putString("ganraoState", loginString).apply();
    }

    private String getFirstLoginState() {
        return  preferences.getString("firstLoginState", "error");
    }
    private String getSecondLoginState() {
        return  preferences.getString("ganraoState", "error");
    }
    private void openMyWeb() {


        final Request request = new Request.Builder().url("https://www.csdn.net").build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("hhapplication", "失败:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.d("hhapplication", "结果:" + result);
            }
        });

    }

    public String getResult() {
        return "123321";
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myTestReceive);
        Log.d("", "SplashActivity...finish ");
        super.onDestroy();
    }
}
