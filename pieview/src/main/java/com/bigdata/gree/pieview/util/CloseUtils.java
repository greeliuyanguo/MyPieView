package com.bigdata.gree.pieview.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Author:liuyanguo
 * Date:2017/10/17
 * Time:11:11
 * Description:
 */

public final class CloseUtils {

    private CloseUtils() {
    }

    /**
     * 关闭Closeable对象
     *
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
