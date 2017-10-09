package com.bigdata.gree.pieview.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bigdata.gree.pieview.R;

public class CanvasActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "CanvasActivity";

    public static final int RECTFRAGMENT = 0;
    public static final int ROTATEFRAGMENT = 1;
    public static final int SKEWFRAGMENT = 2;

    private RectFragment mRectFragment;
    private RotateFragment mRotateFragment;
    private SkewFragment mSkewFragment;

    private Fragment[] fragments;
    private FragmentManager fm = getSupportFragmentManager();
    private int lastIndex = 0;


    private RadioGroup mRadioGroup;
    private RadioButton mRBRect, mRBRotate, mRBSkew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        initFragments(savedInstanceState);
        showFragment(CanvasActivity.RECTFRAGMENT);

        initRadios();
        turn();
    }

    private void turn() {
        findViewById(R.id.turnToPicBmActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CanvasActivity.this.startActivity(new Intent(CanvasActivity.this, PicBmActivity.class));
                CanvasActivity.this.finish();
            }
        });
    }

    private void initRadios() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRBRect = (RadioButton) findViewById(R.id.rb_rect_fragment);
        mRBRotate = (RadioButton) findViewById(R.id.rb_rotate_fragment);
        mRBSkew = (RadioButton) findViewById(R.id.rb_skew_fragment);

        mRBRect.setOnClickListener(this);
        mRBRotate.setOnClickListener(this);
        mRBSkew.setOnClickListener(this);
    }

    private void initFragments(Bundle savedInstanceState) {
        if (null == savedInstanceState) {
            mRectFragment = new RectFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, mRectFragment, "RectFragment")
                    .hide(mRectFragment)
                    .commit();
        } else {
            mRectFragment = (RectFragment) savedInstanceState.get("RectFragment");
        }
        if (null == savedInstanceState) {
            mRotateFragment = new RotateFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, mRotateFragment, "RotateFragment")
                    .hide(mRotateFragment)
                    .commit();
        } else {
            mRotateFragment = (RotateFragment) savedInstanceState.get("RotateFragment");
        }
        if (null == savedInstanceState) {
            mSkewFragment = new SkewFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, mSkewFragment, "SkewFragment")
                    .hide(mSkewFragment)
                    .commit();
        } else {
            mSkewFragment = (SkewFragment) savedInstanceState.get("SkewFragment");
        }

        fragments = new Fragment[]{
                mRectFragment,
                mRotateFragment,
                mSkewFragment
        };
    }

    public void showFragment(int showIndex) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(fragments[lastIndex])
                .show(fragments[showIndex])
                .commit();
        lastIndex = showIndex;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rb_rect_fragment:
                showFragment(CanvasActivity.RECTFRAGMENT);
                break;
            case R.id.rb_rotate_fragment:
                showFragment(CanvasActivity.ROTATEFRAGMENT);
                break;
            case R.id.rb_skew_fragment:
                showFragment(CanvasActivity.SKEWFRAGMENT);
                break;
        }
    }
}
