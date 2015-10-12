package com.example.yuanzheng.preparedemo.base;

import android.app.Application;

import com.example.yuanzheng.preparedemo.network.volley.VolleyUtils;


/**
 * Created by yee on 11/1/13.
 * <p>
 */
public class AppApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppInfo.init(getApplicationContext());
        VolleyUtils.init(getApplicationContext());
    }
}
