package com.bigdata.gree.pieview.http;

import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:liuyanguo
 * Date:2017/10/19
 * Time:14:56
 * Description:
 */

public abstract class Request<T> implements Comparable<Request<T>> {
    //默认编码方式
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    //请求序列号
    protected int mSerialNum = 0;
    //优先级默认设置为Normal
    protected HttpUtils.Priority mPriority = HttpUtils.Priority.NORMAL;
    //是否取消该请求
    protected boolean isCancel = false;
    //该请求是否应该缓存
    private boolean mShouldCache = true;
    //请求Listener
    private RequestListener<T> mRequestListener;
    //请求的URL
    private String mUrl = "";
    //请求的方法
    private HttpUtils.HttpMethod mHttpMethod = HttpUtils.HttpMethod.GET;
    //请求的header
    private Map<String, String> mHeaders = new HashMap<>();
    //请求参数
    private Map<String, String> mBodyParams = new HashMap<>();

    /**
     * 请求的方法
     *
     * @param method   请求方式
     * @param url      请求的目标URL
     * @param listener 请求回调，将结果回调给用户
     */
    public Request(HttpUtils.HttpMethod method, String url, RequestListener<T> listener) {
        mHttpMethod = method;
        mUrl = url;
        mRequestListener = listener;
    }

//    /**
//     * 从原生的网络请求中解析结果，子类必须覆写
//     *
//     * @param response
//     * @return
//     */
//    public abstract T parseResponse(Response response);
//
//    public final void deliveryResponse(Response response) {
//        T result = parseResponse(response);
//        int stCode = 0;
//        String message = "";
//        if (null != mRequestListener) {
//            if (null != result) {
//                stCode = response.getStatusCode();
//            } else {
//                stCode = -1;
//            }
//            if (null != response) {
//                message = response.getMessage();
//            } else {
//                message = "unknow error.";
//            }
//            mRequestListener.onComplete(stCode, result, message);
//        }
//    }

    protected int getSerialNum() {
        return mSerialNum;
    }

    protected Map<String, String> getParams() {
        return mBodyParams;
    }

    protected HttpUtils.Priority getPriority() {
        return mPriority;
    }

    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencodeed ; charset = " + getParamsEncoding();
    }

    /**
     * 返回POST或者PUT请求时的Body参数字节数组
     *
     * @return
     */
    public byte[] getBody() {
        Map<String, String> params = getParams();
        if (null != params && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        }
        return null;
    }

    /**
     * 将参数转换为URL编码的参数串，格式为key1=value1&key2=value2
     *
     * @param params
     * @param paramsEncoding
     * @return
     */
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append("=");
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append("&");
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, e);
        }
    }

    /**
     * 用于处理请求的排序处理，根据优先级和加入到队列的序号进行排序
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NonNull Request<T> o) {
        HttpUtils.Priority myPriority = this.getPriority();
        HttpUtils.Priority oPriority = o.getPriority();
        //如果优先级相等，那么按照添加到队列的序列号顺序来执行
        if (myPriority.equals(o)) {
            return this.getSerialNum() - o.getSerialNum();
        } else {
            return myPriority.ordinal() - oPriority.ordinal();
        }
    }

    /**
     * 网络请求Listener，会被执行在UI线程
     *
     * @param <T>
     */
    public static interface RequestListener<T> {
        //请求完成的回调
        public void onComplete(int stcode, T response, String errMsg);
    }
}
