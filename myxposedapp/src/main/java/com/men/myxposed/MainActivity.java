package com.men.myxposed;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        Log.d("imei", getIMEI(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //63f4a5be0edae6cd
    }

    public String getResult() {
        String str= Settings.System.getString(MainActivity.this.getContentResolver(), "android_id");
        return str;
    }



    public  String getIMEI(Context context) {
        String imei =null;
        @SuppressLint("ServiceCast")
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE )== PermissionChecker.PERMISSION_GRANTED){
             imei = manager.getDeviceId();
        }
        return imei;
    }
}
