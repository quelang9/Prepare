package com.example.yuanzheng.preparedemo.network.protocal;

import com.example.yuanzheng.preparedemo.base.AppInfo;
import com.example.yuanzheng.preparedemo.utils.sign.SignUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yuanzheng on 15/10/13.
 */
public class RequestParamUtils {
    public static final String BODY="body";
    public static final String CALL="call";
    public static final String DATA="data";
    private Body body;
    private RequestParamUtils(){}
    private static RequestParamUtils mInstance;
    public static RequestParamUtils getInstance(){
        if (mInstance == null) {
            synchronized (RequestParamUtils.class) {
                if (mInstance == null) {
                    mInstance = new RequestParamUtils();
                }
            }
        }
        return mInstance;
    }
    public LinkedHashMap<String,String> mParams=new LinkedHashMap<String,String>();

    public RequestParamUtils addDataJson(String dataJson){
        mParams.put(DATA,dataJson);
        return this;
    }

    public RequestParamUtils setCallParams(String api,String version){
        mParams.clear();
        body = new Body().putVersion(AppInfo.appVersion).
                putAppid(AppInfo.imei + "").
                putDevice("android").
                putClient("BOSS").
                putToken(AppInfo.token).
                putCache("123123123");
        mParams.put(BODY, body.toJson());
        mParams.put(CALL,new Call().putApi(api).
                putVersion(version).toJson());
        return  this;
    }

    public Map<String,String> completeFinalParams(){
        body.putHMAC(SignUtil.generateHmac(mParams));
        mParams.put(BODY,body.toJson());
        return mParams;
    }
}
