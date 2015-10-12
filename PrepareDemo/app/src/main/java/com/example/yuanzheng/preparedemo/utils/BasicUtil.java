package com.example.yuanzheng.preparedemo.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 获取手机基础信息
 *
 * @author hankai
 */
public class BasicUtil {


    /**
     * 拨打电话
     */
    public static void dial(Context context, Uri phoneNum) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, phoneNum);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
