package com.bigdata.gree.pieview.http;

import org.json.JSONObject;

/**
 * Author:liuyanguo
 * Date:2017/10/19
 * Time:16:10
 * Description:
 */

public class JsonRequest extends Request<JSONObject> {
    /**
     * 请求的方法
     *
     * @param method   请求方式
     * @param url      请求的目标URL
     * @param listener 请求回调，将结果回调给用户
     */
    public JsonRequest(HttpUtils.HttpMethod method, String url, RequestListener<JSONObject> listener) {
        super(method, url, listener);
    }

//    /**
//     * 将Response的结果转换为JSONObject
//     *
//     * @param response
//     * @return
//     */
//    @Override
//    public JSONObject parseResponse(Response response) {
//        String jsonString = new String(response.getRawData);
//        try {
//            return new JSONObject(jsonString);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
