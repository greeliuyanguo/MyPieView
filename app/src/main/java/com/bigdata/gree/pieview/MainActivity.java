package com.bigdata.gree.pieview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private PieView mPieView;
    private List<PieData> mPieList = new ArrayList<>();
    private Random mRandom = new Random();
    private MyButton mContinuousChange;

    private TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            mPieList.clear();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initPieView();
                }
            });
        }
    };
    private Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPieView = (PieView) this.findViewById(R.id.pie_view);
        initPieView();

        findViewById(R.id.click_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPieList.clear();
                initPieView();
            }
        });

        mContinuousChange = ((MyButton) findViewById(R.id.continuous_change));
        mContinuousChange.setMode(MyButton.MODE_ENABLE);
        mContinuousChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContinuousChange.getMode().equals(MyButton.MODE_ENABLE)) {
                    mTimer.schedule(mTask, 0l, 100l);
                    mContinuousChange.setMode(MyButton.MODE_STOP);
                    mContinuousChange.setText("停    止");
                    mContinuousChange.setTextColor(0x606060);
                } else if (mContinuousChange.getMode().equals(MyButton.MODE_STOP)) {
                    mTimer.cancel();
                    mContinuousChange.setMode(MyButton.MODE_ENABLE);
                    mContinuousChange.setText("连续变换");
                    mContinuousChange.setTextColor(0x000000);
                }
            }
        });
    }

    private void initPieView() {

        PieData pd1 = new PieData("Name1", mRandom.nextFloat());
        PieData pd2 = new PieData("Name2", mRandom.nextFloat());
        PieData pd3 = new PieData("Name3", mRandom.nextFloat());
        PieData pd4 = new PieData("Name4", mRandom.nextFloat());
        PieData pd5 = new PieData("Name5", mRandom.nextFloat());
        PieData pd6 = new PieData("Name6", mRandom.nextFloat());
        PieData pd7 = new PieData("Name7", mRandom.nextFloat());
        PieData pd8 = new PieData("Name8", mRandom.nextFloat());
        PieData pd9 = new PieData("Name9", mRandom.nextFloat());
        PieData pd10 = new PieData("Name10", mRandom.nextFloat());
        PieData pd11 = new PieData("Name11", mRandom.nextFloat());
        PieData pd12 = new PieData("Name12", mRandom.nextFloat());

        mPieList.add(pd1);
        mPieList.add(pd2);
        mPieList.add(pd3);
        mPieList.add(pd4);
        mPieList.add(pd5);
        mPieList.add(pd6);
        mPieList.add(pd7);
        mPieList.add(pd8);
        mPieList.add(pd9);
        mPieList.add(pd10);
        mPieList.add(pd11);
        mPieList.add(pd12);

        mPieView.setData(mPieList);
        mPieView.setStartAngle(0);
    }
}