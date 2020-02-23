package com.men.myxposed.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyObject implements InvocationHandler {
    TargetObject targetObject;

    public void setTargetObject(TargetObject targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d("ssss", "代理前...");
        Object invoke = method.invoke(targetObject, args);
        Log.d("ssss", "代理代理后...");
        return invoke;
    }
}
