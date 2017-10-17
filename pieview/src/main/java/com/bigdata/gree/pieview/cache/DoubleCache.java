package com.bigdata.gree.pieview.cache;

import android.graphics.Bitmap;

/**
 * Author:liuyanguo
 * Date:2017/10/17
 * Time:9:54
 * Description:Double cache.
 */

public class DoubleCache {
    ImageCache mMemoryCache = new ImageCache();
    DiskCache mDiskCache = new DiskCache();

    /**
     * 先从内存中获取缓存，如果没有，则从SD卡中获取
     *
     * @param url
     * @return
     */
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (null == bitmap) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    /**
     * 下载的图片，在内存中以及SD卡中缓存
     *
     * @param url
     * @param bmp
     */
    public void put(String url, Bitmap bmp) {
        mMemoryCache.put(url, bmp);
        mDiskCache.put(url, bmp);
    }
}
