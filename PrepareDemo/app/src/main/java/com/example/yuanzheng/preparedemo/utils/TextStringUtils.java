package com.example.yuanzheng.preparedemo.utils;

import android.content.Context;

/**
 * Created by yaohu on 15/6/5.
 */
public class TextStringUtils {
    /**
     * TextView 文字格式
     *
     * @param ctx
     * @param resId
     * @param value
     * @return
     */
    public static String parseString(Context ctx, int resId, Object value) {
        String str = ctx.getResources().getString(resId);
        return String.format(str, value);
    }
}
