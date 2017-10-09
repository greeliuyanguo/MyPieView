package com.bigdata.gree.pieview.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bigdata.gree.pieview.model.FragmentPagerAdapterModel;

import java.util.List;

/**
 * Author:liuyanguo
 * Date:2017/10/9
 * Time:13:44
 * Description:
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private List<FragmentPagerAdapterModel> mList;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<FragmentPagerAdapterModel> modelList) {
        super(fm);
        this.mFragmentList = list;
        mList = modelList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        FragmentPagerAdapterModel data = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("message", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
