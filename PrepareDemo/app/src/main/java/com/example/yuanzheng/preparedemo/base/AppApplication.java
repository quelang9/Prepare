package com.example.yuanzheng.preparedemo.base;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.example.yuanzheng.preparedemo.network.volley.VolleyUtils;


/**
 * Created by yee on 11/1/13.
 * <p>
 */
public class AppApplication extends com.activeandroid.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        AppInfo.init(getApplicationContext());
        VolleyUtils.init(getApplicationContext());
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
