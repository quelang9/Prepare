package com.example.yuanzheng.preparedemo.network.volleyUtls.request;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.yuanzheng.preparedemo.network.volleyUtls.HTTPSTrustManager;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by liangrenwang on 15/10/13.
 */
public class BaseRequest<T> extends Request<T> {
    private static final String TAG = "VolleyRequest";
    protected ArrayMap<String,String> mParams;
    protected final String mUrl;
    protected final NetListener mListener;
    private static final int SOCKET_TIME = 60*1000;
    public BaseRequest(int method, String url,ArrayMap<String,String> params ,NetListener listener) {
        super(method, url, listener);
        if("https".startsWith(url.toLowerCase()))
        HTTPSTrustManager.allowAllSSL();
        mUrl = url;
        mParams = params;
        mListener = listener;
        if(null == mParams){
            mParams = new ArrayMap<>();
        }
        setRetryPolicy(new DefaultRetryPolicy(SOCKET_TIME, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setShouldCache(false);
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> headers = new HashMap<>();
        headers.put("Charset","UTF-8");
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {

        return mParams;
    }

    @Override
    protected void deliverResponse(T response) {
        if(mListener!=null)
            mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String json = null;
        try {
             json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return (Response<T>) Response.success(json, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (null != volleyError) {
            if (volleyError instanceof NoConnectionError) {
                if (!TextUtils.isEmpty(volleyError.getMessage()) && volleyError.getMessage().equals("java.io.InterruptedIOException")) {
                    return new VolleyError("java.io.InterruptedIOException");
                }
                return new VolleyError("连接服务器失败");
            } else if (volleyError instanceof TimeoutError) {
                return new VolleyError("连接超时，点击重试");
            }
        }
        return super.parseNetworkError(volleyError);
    }
}
