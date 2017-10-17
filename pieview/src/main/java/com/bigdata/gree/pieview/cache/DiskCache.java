package com.bigdata.gree.pieview.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author:liuyanguo
 * Date:2017/10/17
 * Time:9:34
 * Description:The sdcard cache of image.
 */

public class DiskCache {

    private static String cacheDir = "sdcard/cache";

    /**
     * 从SD卡缓存中获取图片
     *
     * @param url
     * @return
     */
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    /**
     * 将图片缓存到SD卡中
     *
     * @param url
     * @param bitmap
     */
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(cacheDir + url);
            //图片压缩存储，以PNG格式，100kb的条件下存储
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
