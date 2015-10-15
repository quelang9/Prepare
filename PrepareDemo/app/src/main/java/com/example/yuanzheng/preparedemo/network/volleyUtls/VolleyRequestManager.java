package com.example.yuanzheng.preparedemo.network.volleyUtls;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by liangrenwang on 15/10/13.
 */
public class VolleyRequestManager {

    private static RequestQueue mRequestQueue;
    private static RequestQueue mHttpsRequestQueue;

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 自定义的安全认证证书，在本地保存验证
     * @param context
     * @param host 服务器地址
     * @param certResId 认证证书
     * @param certPass  认证密码
     */
    public static void initHttps(Context context,String host,int certResId,String certPass) {
        HttpsClientStack stack = new HttpsClientStack(new String[]{host},new int[]{certResId},new String[]{certPass});
        mHttpsRequestQueue = stack.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static RequestQueue getHttpsRequestQueue() {
        if(mHttpsRequestQueue !=null)
            return mHttpsRequestQueue;
        else
            throw new IllegalStateException("RequestQueue not initalized");
    }

    public static void cancel(Object tag) {
        getRequestQueue().cancelAll(tag);
    }

    public static void cancelHttps(Object tag) {
        getHttpsRequestQueue().cancelAll(tag);
    }

    public static void addAndStart(com.android.volley.Request request) {
        getRequestQueue().add(request);
        getRequestQueue().start();
    }

    public static void addAndStart(com.android.volley.Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
        getRequestQueue().start();
    }
    public static void addAndStartHttps(com.android.volley.Request request) {
        getHttpsRequestQueue().add(request);
        getHttpsRequestQueue().start();
    }

    public static void addAndStartHttps(com.android.volley.Request request, String tag) {
        request.setTag(tag);
        getHttpsRequestQueue().add(request);
        getHttpsRequestQueue().start();
    }
}
