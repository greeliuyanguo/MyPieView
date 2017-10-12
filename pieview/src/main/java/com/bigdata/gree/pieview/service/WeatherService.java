package com.bigdata.gree.pieview.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.bigdata.gree.pieview.R;
import com.bigdata.gree.pieview.view.MainActivity;

/**
 * Author:liuyanguo
 * Date:2017/10/12
 * Time:10:57
 * Description:
 * [思路描述]
 * --1.用NotificationCompat.Builder的内部类（构造者模式）构建一个mBuilder，最后用mBuilder来构建一个通知Notification
 * ------1.1 用mBuilder的setSmallIcon(),setContentTitle(),setContentText()来具体配置Notification的
 * ------1.2 mBuilder需要设置ContentIntent,这个方法中需要传递一个PendingIntent的类对象
 * ----------1.2.1 Intent负责跳转效果 --> 构建成TaskStackBuilder任务栈----[stackBuilder.addNextIntent(resultIntent)]
 * ----------1.2.2 任务栈TaskStackBuilder -->构建成PendingIntent----[PendingIntent resultPendingIntent =
 * ----------------------------------------------------------------------stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);]
 * --2.构建完通知Notification之后，调用系统服务NotificationManager来进行直接唤醒Notification显示在通知栏上，
 * --最后startForeground()启动为前台服务
 */

public class WeatherService extends Service {

    private static final int NOTIFY_ID = -1;

    @Override
    public void onCreate() {
        super.onCreate();

        showNotification();
    }

    /**
     * 在通知栏显示天气消息
     */
    private void showNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("这是标题")
                        .setContentText("这是内容");

        //通知的点击效果触发的点击事件
        Intent resultIntent = new Intent(this, MainActivity.class);

        //创建任务栈
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        //获取通知任务管理器
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //构建通知
        final Notification notification = mBuilder.build();
        //显示通知
        mNotifyMgr.notify(NOTIFY_ID, notification);

        startForeground(NOTIFY_ID, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
