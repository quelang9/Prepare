package com.example.yuanzheng.preparedemo.network.request;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.yuanzheng.preparedemo.base.AppInfo;
import com.example.yuanzheng.preparedemo.network.volley.InterruptedIOExceptionError;
import com.example.yuanzheng.preparedemo.network.volley.NetDisableError;
import com.example.yuanzheng.preparedemo.network.volley.NetListener;
import com.example.yuanzheng.preparedemo.utils.logger.LogUtil;
import com.example.yuanzheng.preparedemo.utils.sign.SignUtil;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by yefeng on 6/1/15.
 * github:yefengfreedom
 */
public class BaseRequest<T> extends Request<T> {

    private static final String TAG = "net";

    private static final String GZIP_KEY = "Accept-Encoding";
    private static final String GZIP_VALUE = "gzip";
    private static final int SOCKET_TIMEOUT = 60 * 1000;
    protected final NetListener mListener;
    protected final String mUrl;
    protected Map<String, String> mParams;

    public BaseRequest(int method, String url, Map<String, String> params, NetListener listener) {
        super(method, url, listener);
        mUrl = url;
        mParams = params;
        if (null == mParams) {
            mParams = new HashMap<String, String>();
        }
        SignUtil.addSignKeyParams(mParams);
        mListener = listener;
        // set connection time out and retry times
        setRetryPolicy(new DefaultRetryPolicy(SOCKET_TIMEOUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setShouldCache(false);
        if (method == Method.POST) {
            LogUtil.basic_d(TAG, "post request: " + url);
        } else if (method == Method.GET) {
            LogUtil.basic_d(TAG, "get request: " + url);
        } else {
            LogUtil.basic_d(TAG, method + " request: " + url);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Charset", "UTF-8");
//        headers.put("Content-Type", "application/x-javascript");
//        headers.put("Accept-Encoding", "gzip,deflate");
//        headers.put("Cookie", AppInfo.cookieFromResponse);
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (null != mParams) {
            LogUtil.basic_d(TAG, "request params: " + mParams.toString());
        }
        return mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
//            if (response.headers.containsKey(GZIP_KEY) && response.headers.containsValue(GZIP_VALUE)) {
//                json = getRealString(response.data);
//            }
            return (Response<T>) Response.success(
                    json, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return Response.error(new ParseError(e));
//        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        LogUtil.basic_d(TAG, "http error: " + volleyError.getLocalizedMessage());
        if (null != volleyError) {
            if (volleyError instanceof NoConnectionError) {
                if (!TextUtils.isEmpty(volleyError.getMessage()) && volleyError.getMessage().equals("java.io.InterruptedIOException")) {
                    return new InterruptedIOExceptionError();
                }
                return new NetDisableError("连接服务器失败");
            } else if (volleyError instanceof TimeoutError) {
                return new NetDisableError("连接超时，点击重试");
            }
        }
        return super.parseNetworkError(volleyError);
    }

    /**
     * parse gzip
     *
     * @param data data
     * @return real string
     */
    private String getRealString(byte[] data) {
        byte[] h = new byte[2];
        h[0] = (data)[0];
        h[1] = (data)[1];
        int head = getShort(h);
        boolean t = head == 0x1f8b;
        InputStream in;
        StringBuilder sb = new StringBuilder();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            if (t) {
                in = new GZIPInputStream(bis);
            } else {
                in = bis;
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private int getShort(byte[] data) {
        return (data[0] << 8) | data[1] & 0xFF;
    }


}
