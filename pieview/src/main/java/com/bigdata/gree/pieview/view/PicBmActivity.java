package com.bigdata.gree.pieview.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bigdata.gree.pieview.MyApplication;
import com.bigdata.gree.pieview.R;
import com.bigdata.gree.pieview.adapter.MyViewPagerAdapter;
import com.bigdata.gree.pieview.model.FragmentPagerAdapterModel;
import com.bigdata.gree.pieview.reveiver.MyBroadcastReceiver;
import com.bigdata.gree.pieview.service.WeatherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PicBmActivity extends AppCompatActivity {

    private static final String TAG = "PicBmActivity";

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private List<FragmentPagerAdapterModel> mData;
    private MyViewPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private FloatingActionButton mFab;
    private ProgressDialog mPDialog;

    private MyBroadcastReceiver mReceiver;

    private PictureFragment mPictureFragment, mPictureFragment2, mPictureFragment3;
    private BitmapFragment mBitmapFragment, mBitmapFragment2;

    private int[] resIds = new int[]{
            R.raw.cmdut1ils,
            R.raw.cmdutils,
            R.raw.cmdutils_opencl,
            R.raw.configure,
            R.raw.ff1mpeg,
            R.raw.ffm1peg_cuvid,
            R.raw.ffmp1eg,
            R.raw.ffmp1eg_hw,
            R.raw.ffmp1eg_opt,
            R.raw.ffmpeg1_qsv,
            R.raw.ffmpeg_fi1lter,
            R.raw.ffmpeg_videotoolb1ox,
            R.raw.ffplay,
            R.raw.ffprobe,
            R.raw.ffserver,
            R.raw.ffserver_c1onfig,
            R.raw.ffserver_config,
            R.raw.gitattributes,
            R.raw.gitignore,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_bm);

        initField();
        initViewPager();
        initTabLayout();
        initFab();
        initReceiver();
        initProgressDialog();
    }

    private void initProgressDialog() {

        mPDialog = new ProgressDialog(this);
        mPDialog.setProgress(0);
        mPDialog.setMax(100);
        mPDialog.setCancelable(false);

        mPDialog.setTitle("提示");
        mPDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mPDialog.setIcon(R.mipmap.ic_launcher);
        mPDialog.setMessage("正在上传...");
        mPDialog.setCanceledOnTouchOutside(false);
    }


    private void initReceiver() {
        registerReceiver(mReceiver, new IntentFilter("HelloWorld"));
    }

    private void initFab() {
        mFab = (FloatingActionButton) findViewById(R.id.floatActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (MyApplication.sCurrentIndex) {
                    case 0:
                        PicBmActivity.this.sendBroadcast(new Intent("HelloWorld"));
                        break;
                    case 1:
                        PicBmActivity.this.startService(new Intent(PicBmActivity.this, WeatherService.class));
                        break;
                    case 2:
                        new UploadFilesTask().execute();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
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

    class UploadFilesTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            mPDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            int fileCounts = 0;
            for (int i = 0; i < resIds.length; i++) {
                BufferedReader br = null;
                try {
                    String line = "";
                    br = new BufferedReader(new InputStreamReader(getResources().openRawResource(resIds[i])));
                    while (null != (line = br.readLine())) {
                    }
                    fileCounts++;
                    int progress = (int) ((fileCounts / (float) resIds.length) * 100);
                    Thread thread = getMainLooper().getThread();
                    thread.sleep(500);
                    publishProgress(progress);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (null != br) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return fileCounts;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            mPDialog.dismiss();
            Toast.makeText(PicBmActivity.this, "已请求完，数量：" + integer, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mPDialog.setProgress(Integer.valueOf(values[0]));
        }
    }
}
