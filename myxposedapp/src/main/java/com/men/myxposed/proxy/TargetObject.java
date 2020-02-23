package com.men.myxposed.proxy;

import android.util.Log;

public class TargetObject implements IProxy {
    @Override
    public void proxyMethod() {
        Log.d("proxyMethod", "proxyMethod 方法 start");
    }
}
