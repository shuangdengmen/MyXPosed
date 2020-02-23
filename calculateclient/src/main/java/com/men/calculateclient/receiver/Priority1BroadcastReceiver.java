package com.men.calculateclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.men.calculateclient.activity.BroadcastActivity;

public class Priority1BroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();//获取有序广播的数据
        System.out.println("优先级为1的接收到的广播数据resultData---->" + resultData);
        Bundle resultExtras = getResultExtras(false);
        String string = resultExtras.getString(BroadcastActivity.ORDER_DATA);
        System.out.println("优先级为1的接收到的广播数据resultExtras---->" + string);
        setResultData("优先级为1的setResultData的数据");//修改有序广播的数据
    }
}
