package com.example.yuanzheng.preparedemo.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by hankai on 15/6/23.
 */
public class PhoneUtil {
    /**
     * 获取IMEI
     *
     * @return
     */
    public static String getIMEI(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
