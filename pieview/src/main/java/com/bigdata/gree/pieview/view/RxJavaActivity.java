package com.bigdata.gree.pieview.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bigdata.gree.pieview.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        initRx();
    }

    private void initRx() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                //调用完成
            }

            @Override
            public void onError(Throwable e) {
                //调用出错
            }

            @Override
            public void onNext(String s) {
                //要执行的耗时操作
            }
        };

        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }
        };
        if (!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }

        final Observable observable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                subscriber.onNext("Hello");
                subscriber.onNext("World");
                subscriber.onNext("Android");
                subscriber.onCompleted();
                subscriber.onError(null);
            }
        });
    }
}
