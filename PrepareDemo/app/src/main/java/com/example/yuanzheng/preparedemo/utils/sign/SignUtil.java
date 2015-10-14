package com.example.yuanzheng.preparedemo.utils.sign;

import android.content.Context;
import android.text.TextUtils;

import com.example.yuanzheng.preparedemo.base.AppInfo;
import com.example.yuanzheng.preparedemo.network.protocal.RequestParamUtils;
import com.example.yuanzheng.preparedemo.utils.MD5;
import com.example.yuanzheng.preparedemo.utils.SharedPreferenceUtil;
import com.example.yuanzheng.preparedemo.utils.constant.ConstantUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * request sign_key util
 */
public class SignUtil {

    public static String generateHmac(LinkedHashMap<String, String> params){
        if(params==null){
            throw new IllegalArgumentException("The request params can not be null");
        }

        StringBuilder builder=new StringBuilder(4);
        builder.append(params.get(RequestParamUtils.BODY));
        builder.append(params.get(RequestParamUtils.CALL));
        builder.append(params.get(RequestParamUtils.DATA));
        builder.append(ConstantUtils.SALT);
        String encryptionS="";
        try {
            encryptionS=MD5.getMessageDigest(builder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            encryptionS = "";
        }
        return encryptionS;

    }




}