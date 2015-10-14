package com.example.yuanzheng.preparedemo.network.protocal;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yuanzheng on 15/10/13.
 */
public class Call {
    public static  final String API="api";
    public static  final String VERSION="version";
    public Map<String,String> mCallContent=new LinkedHashMap<String,String>();

    public Call putApi(String api){
        mCallContent.put(API,api);
        return this;
    }

    public Call putVersion(String version){
        mCallContent.put(VERSION,version);
        return this;
    }

    public String toJson(){
        return new Gson().toJson(mCallContent);
    }
}
