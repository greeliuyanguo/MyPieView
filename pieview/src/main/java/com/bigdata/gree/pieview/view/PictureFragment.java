package com.bigdata.gree.pieview.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigdata.gree.pieview.R;
import com.bigdata.gree.pieview.model.FragmentPagerAdapterModel;

/**
 * Author:liuyanguo
 * Date:2017/10/9
 * Time:14:01
 * Description:
 */

public class PictureFragment extends Fragment {

    private static final String TAG = "PictureFragment";

    private FragmentPagerAdapterModel mModel;
    private TextView mName, mSex, mAge;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mModel = (FragmentPagerAdapterModel) bundle.getParcelable("message");
        Log.d(TAG, mModel.toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View view) {
        mName = view.findViewById(R.id.pic_fragment_name);
        mSex = view.findViewById(R.id.pic_fragment_sex);
        mAge = view.findViewById(R.id.pic_fragment_age);

        mName.setText(mModel.getName());
        mSex.setText(mModel.getSex());
        mAge.setText(mModel.getAge() + "");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
