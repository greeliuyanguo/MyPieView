package com.bigdata.gree.pieview.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bigdata.gree.pieview.MyApplication;
import com.bigdata.gree.pieview.R;
import com.bigdata.gree.pieview.adapter.MyViewPagerAdapter;
import com.bigdata.gree.pieview.model.FragmentPagerAdapterModel;
import com.bigdata.gree.pieview.reveiver.MyBroadcastReceiver;
import com.bigdata.gree.pieview.service.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class PicBmActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private List<FragmentPagerAdapterModel> mData;
    private MyViewPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private FloatingActionButton mFab;

    private MyBroadcastReceiver mReceiver;

    private PictureFragment mPictureFragment, mPictureFragment2, mPictureFragment3;
    private BitmapFragment mBitmapFragment, mBitmapFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_bm);

        initField();
        initViewPager();
        initTabLayout();
        initFab();
        initReceiver();

    }

    private void initReceiver() {
        registerReceiver(mReceiver, new IntentFilter("HelloWorld"));
    }

    private void initFab() {
        mFab = (FloatingActionButton) findViewById(R.id.floatActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.sCurrentIndex % 2 == 0) {
                    PicBmActivity.this.sendBroadcast(new Intent("HelloWorld"));
                } else if (MyApplication.sCurrentIndex % 2 == 1) {
                    PicBmActivity.this.startService(new Intent(PicBmActivity.this, WeatherService.class));
                }
            }
        });
    }

    private void initField() {

        mPictureFragment = new PictureFragment();
        mPictureFragment2 = new PictureFragment();
        mBitmapFragment = new BitmapFragment();
        mBitmapFragment2 = new BitmapFragment();
        mPictureFragment3 = new PictureFragment();

        mFragments = new ArrayList<>();
        mFragments.add(mPictureFragment);
        mFragments.add(mBitmapFragment);
        mFragments.add(mPictureFragment3);
        mFragments.add(mBitmapFragment2);
        mFragments.add(mPictureFragment2);

        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FragmentPagerAdapterModel model = new FragmentPagerAdapterModel();
            model.setName(i + "_name");
            model.setSex(i + "_sex");
            model.setAge(i + 100);
            mData.add(model);
        }

        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mFragments, mData);
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MyApplication.sCurrentIndex = position;
                mTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("标题1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("标题2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("标题3"));
        mTabLayout.addTab(mTabLayout.newTab().setText("标题4"));
        mTabLayout.addTab(mTabLayout.newTab().setText("标题5"));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MyApplication.sCurrentIndex = tab.getPosition();
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mReceiver) {
            unregisterReceiver(mReceiver);
        }
    }
}
