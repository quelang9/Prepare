package com.example.yuanzheng.preparedemo.utils.umeng;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 *
 * 页面统计相关类 包含点击次数， 点击特殊属性，与存留时间的统计
 * Created by hankai on 15/7/29.
 */
public class StatisticsUtil {
    /**
     * 统计特定ID点击量
     */
    public static void onItemClick(Context mContext, String ID){
        MobclickAgent.onEvent(mContext, ID);
    }

    /**
     * 计算事件
     * @param mContext
     * @param eventID
     * @param map
     * @param du 浏览时间，需要自己在代码中实现
     */
    public static void onItemClick(Context mContext, String eventID, HashMap<String,String> map, int du){
        MobclickAgent.onEventValue(mContext, eventID, map, du);
    }




}
