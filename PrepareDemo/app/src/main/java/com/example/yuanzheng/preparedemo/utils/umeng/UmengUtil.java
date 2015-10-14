package com.example.yuanzheng.preparedemo.utils.umeng;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by hankai on 15/6/8.
 */
public class UmengUtil {


    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }
}
