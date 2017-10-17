package com.bigdata.gree.pieview.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:liuyanguo
 * Date:2017/10/17
 * Time:9:13
 * Description:
 */

public class ImageLoader {

    //内存缓存
    private ImageCache mImageCache = new ImageCache();

    //SD卡缓存
    private DiskCache mDiskCache = new DiskCache();

    //双缓存
    private DoubleCache mDoubleCache = new DoubleCache();

    //是否使用SD卡缓存
    private boolean isUseDiskCache = false;

    //是否使用双缓存
    private boolean isUseDoubleCache = false;

    //线程池，线程数量为CPU的数量
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 显示图片
     *
     * @param url       图片的url
     * @param imageView 图片控件
     */
    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bmp = null;
        if (isUseDoubleCache) {
            bmp = mDoubleCache.get(url);
        } else if (isUseDiskCache) {
            bmp = mDiskCache.get(url);
        } else {
            bmp = mImageCache.get(url);
        }
        if (null != bmp) {
            imageView.setImageBitmap(bmp);
            return;
        }
        //把图片的url作为Tag存储起来
        imageView.setTag(url);
        //将耗时操作提交到线程池里面去
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (null == bitmap) {
                    return;
                }
                //等下载完图片进行url的比对，如果url一致则认定下载的与原定的是一致的
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                //将下载完的图片缓存到内存中去，LruCache<String,Bitmap>
                mImageCache.put(url, bitmap);
                mDiskCache.put(url, bitmap);
            }
        });
    }

    /**
     * 下载图片的方法
     *
     * @param imageUrl 图片的url
     * @return
     */
    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 是否使用SD卡缓存
     *
     * @param useDiskCache
     */
    public void useDiskCache(boolean useDiskCache) {
        isUseDiskCache = useDiskCache;
    }

    /**
     * 是否使用双缓存
     *
     * @param useDoubleCache
     */
    public void useDoubleCache(boolean useDoubleCache) {
        isUseDoubleCache = useDoubleCache;
    }
}
