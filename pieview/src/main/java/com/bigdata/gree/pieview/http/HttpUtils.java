package com.bigdata.gree.pieview.http;

/**
 * Author:liuyanguo
 * Date:2017/10/19
 * Time:14:37
 * Description:
 */

public class HttpUtils {

    /**
     * 请求方法枚举
     */
    public static enum HttpMethod {

        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        private String mHttpMethod = "";

        private HttpMethod(String method) {
            mHttpMethod = method;
        }

        public String getMethod() {
            return mHttpMethod;
        }
    }

    /**
     * 优先级枚举
     */
    public static enum Priority {
        LOW,
        NORMAL,
        HIGN,
        IMMEDIATE;
    }
}
