package com.example.yuanzheng.preparedemo.utils.sign;

import android.content.Context;
import android.text.TextUtils;

import com.example.yuanzheng.preparedemo.base.AppInfo;
import com.example.yuanzheng.preparedemo.utils.MD5;
import com.example.yuanzheng.preparedemo.utils.SharedPreferenceUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yefeng on 7/2/15.
 * github:yefengfreedom
 * <p>
 * request sign_key util
 */
public class SignUtil {
    private static final String SIGN_KEY = "sign_key";

    public static String getSignKey(Context mContext) {
        return SharedPreferenceUtil.getString(mContext, SIGN_KEY, "");
    }

    public static void setSignKey(Context mContext, String sign) {
        if (TextUtils.isEmpty(sign) || "null".equals(sign)) {
            return;
        }
        SharedPreferenceUtil.putString(mContext, SIGN_KEY, sign);
        AppInfo.sign_key = sign;
    }

    public static void addSignKeyParams(Map<String, String> params) {
        if (null == params) {
            params=new HashMap<String,String>();
        }
        if (AppInfo.appUid == null) {
            AppInfo.appUid = "";
        }
        params.put("user_app_id", AppInfo.appUid);
        List<Map.Entry<String, String>> list =
                new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> lhs, Map.Entry<String, String> rhs) {
                return lhs.getKey().compareTo(rhs.getKey());
            }
        });
        String s = "";
        int size = params.size();
        for (int i = 0; i < size; i++) {
            Map.Entry<String, String> ent = list.get(i);
            s += ent.getKey() + "=" + ent.getValue() + "&";
        }
        s += "key=" + AppInfo.sign_key;
        String encryptionS;
        try {
            encryptionS = MD5.getMessageDigest(s.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            encryptionS = "";
        }
        params.put("user_sign", encryptionS);
    }




}