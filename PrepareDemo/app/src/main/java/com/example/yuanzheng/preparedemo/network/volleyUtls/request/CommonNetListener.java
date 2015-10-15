package com.example.yuanzheng.preparedemo.network.volleyUtls.request;

import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.example.yuanzheng.preparedemo.dbManager.AsyncDbManager;
import com.example.yuanzheng.preparedemo.dbManager.CacheUtils;
import com.example.yuanzheng.preparedemo.network.volleyUtls.BaseEntity;
import com.example.yuanzheng.preparedemo.network.volleyUtls.NetStatus;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by liangrenwang on 15/10/13.
 */
public  class CommonNetListener implements NetListener {

    private NetListener netListener;
    private String api;

    public CommonNetListener(String api,NetListener netListener) {
        this.api = api;
        this.netListener = netListener;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if(null != netListener){
            if(null == error || TextUtils.isEmpty(error.getMessage())){
                error = new VolleyError("服务器异常");
            }
            netListener.onErrorResponse(error);
        }
    }

    @Override
    public void onResponse(Object response) {
        if(null == response){
            onErrorResponse(new VolleyError("服务器无响应"));
            return;
        }
        BaseEntity entity = null;
        try {
            Gson gson = new Gson();
            entity = gson.fromJson(response.toString(),BaseEntity.class);
        } catch (Exception e) {
           onErrorResponse(new VolleyError("服务器数据异常"));
            return;
        }
        if(null == entity){
            onErrorResponse(new VolleyError("服务器数据异常"));
            return;
        }
        switch (entity.status){
            case NetStatus.NEED_LOGIN:
                break;
            case NetStatus.INIT_KEY_ILLEGAL:
                break;
            case NetStatus.SUCCESS:
                onCacheResponse(entity);
                break;
        }

    }

    public void onCacheResponse(BaseEntity entity) {
        String data = "";
        if(entity.cache!=null&& NetStatus.CACHE_UNMODIFIED==entity.cache.type){
            data = CacheUtils.getInstances().getValue(api).data;
        }else {
            data =  entity.data.toString();
        }
        if(null != netListener)
            netListener.onResponse(data);
        AsyncDbManager asyncDbManager = new AsyncDbManager();
        asyncDbManager.execute(this.api,entity);
    }
}
