package com.example.yuanzheng.preparedemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuanzheng on 15/6/7.
 */
public class FormatUtils {
    public static String format(String format, double num) {
        return new java.text.DecimalFormat(format).format(num);
    }

    public static String formatTowDecimal(double num) {
        return format("0.00", num);
    }

    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }
}
