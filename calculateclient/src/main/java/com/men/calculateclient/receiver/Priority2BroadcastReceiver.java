package com.men.calculateclient.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.men.calculateclient.MainActivity;
import com.men.calculateclient.activity.BroadcastActivity;

public class Priority2BroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean orderedBroadcast = isOrderedBroadcast();

        if (orderedBroadcast) {
            System.out.println("优先级为2的接收者接收到的是有序广播");
            setResultData("优先级为2的setResultData的数据");//修改有序广播的数据

        } else {
            System.out.println("优先级为2的接收者接收到的是无序广播");
        }


        Bundle bundle=getResultExtras(false);
        if (bundle != null) {
            String orderData = bundle.getString(BroadcastActivity.ORDER_DATA);
            System.out.println("Priority2BroadcastReceiver-------->" + orderData);
        }

        String resultData = getResultData();//获取有序广播的数据
        System.out.println("优先级为2的接收到的广播数据resultData---->" + resultData);

    }
}
