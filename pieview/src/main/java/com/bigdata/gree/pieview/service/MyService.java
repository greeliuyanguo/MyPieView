package com.bigdata.gree.pieview.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Author:liuyanguo
 * Date:2017/10/12
 * Time:10:28
 * Description:默认执行在主线程的Service
 */

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void doMyJob(Intent intent) {
        //从Intent中获取数据
        //执行相关操作

        new Thread(new Runnable() {
            @Override
            public void run() {
                //耗时操作
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
