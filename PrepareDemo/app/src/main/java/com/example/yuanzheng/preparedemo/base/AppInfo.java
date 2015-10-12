package com.example.yuanzheng.preparedemo.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.example.yuanzheng.preparedemo.R;
import com.example.yuanzheng.preparedemo.utils.FileUtils;
import com.example.yuanzheng.preparedemo.utils.MetaDataUtil;
import com.example.yuanzheng.preparedemo.utils.SharedPreferenceUtil;
import com.example.yuanzheng.preparedemo.utils.sign.SignUtil;

import java.io.File;
import java.util.UUID;


/**
 * Created by yee on 11/18/13.
 *
 * @author yefeng
 */
public class AppInfo {

    public static String imei;
    public static String dType;
    public static String dVersion;

    public static int appCode;
    public static String appVersion;
    public static String channel;
    public static String appName;

    public static int width;
    public static int height;
    public static float density;
    public static int densityDpi;
    public static double latitude;
    public static double longtitude;

    public static int appId = 1;
    public static String appUid;

    public static String sign_key;

    /**
     * 当前城市
     */
    public static String currentCity;

    public AppInfo() {
        super();
    }

    public static void init(Context mContext) {
        initImei(mContext);
        channel = MetaDataUtil.getMetaData(mContext, "UMENG_CHANNEL");
        dType = Build.MODEL;
        dVersion = Build.VERSION.SDK_INT + "_" + Build.VERSION.RELEASE;
        PackageInfo pi = null;
        AppInfo.appUid = SharedPreferenceUtil.getString(mContext, "app_uid", "");
        try {
            pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != pi) {
            appVersion = pi.versionName;
            appCode = pi.versionCode;
        } else {
            appVersion = "";
            appCode = 0;
        }
        appName = mContext.getString(R.string.app_name);
        initDisplay(mContext);

        sign_key = SignUtil.getSignKey(mContext);
    }

    private static void initImei(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        imei = tm.getDeviceId();
        if (!TextUtils.isEmpty(imei)) {
            return;
        }
        imei = SharedPreferenceUtil.getString(mContext, "random_imei", "");
        if (!TextUtils.isEmpty(imei)) {
            return;
        }
        String newImei = UUID.randomUUID().toString();
        String path = Environment.getExternalStorageDirectory().toString() + "/System/File/.dachu";
        File dir = new File(path);
        try {
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    FileUtils.writeFile(path + "/.imei", newImei);
                    saveImei(newImei, mContext);
                } else {
                    saveImei(newImei, mContext);
                }
            } else {
                File uuidFile = new File(dir, ".imei");
                if (uuidFile.exists()) {
                    StringBuilder sb = FileUtils.readFile(uuidFile, "utf-8");
                    if (TextUtils.isEmpty(sb)) {
                        //说明上次文件没有写入成功,所以也不需要再次写入了
                        saveImei(newImei, mContext);
                    } else {
                        saveImei(sb.toString(), mContext);
                    }
                } else {
                    saveImei(newImei, mContext);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            saveImei(newImei, mContext);
        }
    }

    private static void saveImei(String newImei, Context mContext) {
        SharedPreferenceUtil.putString(mContext, "random_imei", newImei);
        AppInfo.imei = newImei;
    }

    public static void initDisplay(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        density = metrics.density;
        densityDpi = metrics.densityDpi;
    }
}
