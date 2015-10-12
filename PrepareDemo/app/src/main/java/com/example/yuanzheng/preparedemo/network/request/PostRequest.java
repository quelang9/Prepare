package com.example.yuanzheng.preparedemo.network.request;



import com.example.yuanzheng.preparedemo.network.volley.NetListener;

import java.util.Map;

/**
 * Created by yefeng on 6/1/15.
 * github:yefengfreedom
 */
public class PostRequest extends BaseRequest {
    public PostRequest(String url, Map<String, String> params, NetListener listener) {
        super(Method.POST, url, params, listener);
    }
}
