package com.bigdata.gree.pieview.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Author:liuyanguo
 * Date:2017/10/12
 * Time:10:52
 * Description:
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        this(null);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //这里执行耗时操作
    }
}
