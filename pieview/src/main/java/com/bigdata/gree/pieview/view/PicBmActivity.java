package com.bigdata.gree.pieview.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bigdata.gree.pieview.R;
import com.bigdata.gree.pieview.adapter.MyViewPagerAdapter;
import com.bigdata.gree.pieview.model.FragmentPagerAdapterModel;

import java.util.ArrayList;
import java.util.List;

public class PicBmActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private List<FragmentPagerAdapterModel> mData;
    private MyViewPagerAdapter mAdapter;

    private PictureFragment mPictureFragment;
    private BitmapFragment mBitmapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_bm);

        initField();
        initViewPager();
    }

    private void initField() {

        mPictureFragment = new PictureFragment();
        mBitmapFragment = new BitmapFragment();

        mFragments = new ArrayList<>();
        mFragments.add(mPictureFragment);
        mFragments.add(mBitmapFragment);

        mData = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
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
    }
}
