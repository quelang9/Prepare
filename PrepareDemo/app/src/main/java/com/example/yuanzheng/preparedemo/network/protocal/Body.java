package com.example.yuanzheng.preparedemo.network.protocal;



import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yuanzheng on 15/10/13.
 */
public class Body {
    public static final String VERSION="version";
    public static final String APPID="appid";
    public static final String HMAC="hmac";
    public static final String DEVICE="device";
    public static final String CLIENT="client";
    public static final String TOKEN="token";
    public static final String CACHE="cache";
    public Map<String,String> mBodyContent=new LinkedHashMap<String,String>();

    public Body putVersion(String version){
        mBodyContent.put(VERSION,version);
        return this;
    }
    public Body putAppid(String appid){
        mBodyContent.put(APPID,appid);
        return this;
    }
    public Body putHMAC(String hmac){
        mBodyContent.put(HMAC,hmac);
        return this;
    }
    public Body putDevice(String device){
        mBodyContent.put(DEVICE,device);
        return this;
    }
    public Body putClient(String client){
        mBodyContent.put(CLIENT,client);
        return this;
    }
    public Body putToken(String token){
        mBodyContent.put(TOKEN,token);
        return this;
    }
    public Body putCache(String cache){
        mBodyContent.put(CACHE, cache);
        return this;
    }

    public String toJson(){
       return new Gson().toJson(mBodyContent);
    }
}
