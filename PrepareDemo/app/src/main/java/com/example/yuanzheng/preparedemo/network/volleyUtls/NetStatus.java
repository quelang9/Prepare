package com.example.yuanzheng.preparedemo.network.volleyUtls;

/**
 * Created by liangrenwang on 15/10/13.
 */
public final class NetStatus {

    public static final String DATA = "data";
    public static final String MSG = "msg";
    public static final String STATUS = "status";
    public static final String CACHE= "cache";


    /**
     * response status need login
     */
    public static final int NEED_LOGIN = -100;
    /**
     * init key is disable, need re init
     */
    public static final int NEED_RE_INIT = -10;
    /**
     * init key is illegal, need re init
     */
    public static final int INIT_KEY_ILLEGAL = -2;

    /**
     * response status is success
     */
    public static final int SUCCESS = 0;


    /****不需要CACHE***/
    public static final String CACHE_NONE="NONE";

    /**
     * 保存一定时间
     */
    public static final String CACHE_LOCAL="LOCAL";

    /**
     * 需要根据时间戳更新
     */
    public static final String CACHE_QUERY = "QUERY";

    /**
     * 不需要更新
     */
    public static final String CACHE_UNMODIFIED="UNMODIFIED";

}
