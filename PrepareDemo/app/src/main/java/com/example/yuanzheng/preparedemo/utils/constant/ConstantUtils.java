package com.example.yuanzheng.preparedemo.utils.constant;


/**
 * 描述：常量.
 */
public class ConstantUtils {
    /**
     * 调试模式为true  上线模式为false
     */
    public static final boolean DEBUG = true;
    public static final String BASEURL = DEBUG ? "http://app.chu.test.dachuwang.com/v2" : "http://app.chu.dachuwang.com/v2";


    /**
     * 显示Toast.
     */
    public static final int SHOW_TOAST = 0;

    /**
     * 显示进度框.
     */
    public static final int SHOW_PROGRESS = 1;

    /**
     * 删除进度框.
     */
    public static final int REMOVE_PROGRESS = 2;

    /**
     * 删除底部进度框.
     */
    public static final int REMOVE_DIALOGBOTTOM = 3;
    public static final String SALT="e1f223373ffe55dc18b2e788b77459eb52767021";

    /**
     * 删除中间进度框.
     */
    public static final int REMOVE_DIALOGCENTER = 4;

    /**
     * Dialog的类型.
     */
    public static final int DIALOGPROGRESS = 0;

    /**
     * The Constant DIALOGBOTTOM.
     */
    public static final int DIALOGBOTTOM = 1;

    /**
     * The Constant DIALOGCENTER.
     */
    public static final int DIALOGCENTER = 2;

    //----------------------自定义事件ID -----------------------------//

    /***
     * 事件有两种
     * 一种是只统计点击计数点击，只是简单的统计点击量，调用StatisticsUtil.onItemClick(Context,ID)即可
     * 另外一种是点击事件，可以添加hashMap对不同类别进行统计，调用onItemClick(Context, ID, map, time)
     */

}
