package com.bigdata.gree.pieview.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Author:liuyanguo
 * Date:2017/10/17
 * Time:9:29
 * Description:The memory cache of image.
 */

public class ImageCache {

    //图片缓存
    private LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
        initImageCache();
    }

    /**
     * 初始化图片缓存
     */
    private void initImageCache() {
        //计算机可以使用的最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取1/4的可用内存作为缓存
        int cacheSize = maxMemory / 4;

        mImageCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    /**
     * 存储图片
     *
     * @param url
     * @param bitmap
     */
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    /**
     * 获取图片
     *
     * @param url
     */
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
