package com.bigdata.gree.pieview.reveiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Author:liuyanguo
 * Date:2017/10/12
 * Time:17:50
 * Description:
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hello,World", Toast.LENGTH_SHORT).show();
    }
}
