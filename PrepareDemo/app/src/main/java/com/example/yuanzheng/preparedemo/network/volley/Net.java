package com.example.yuanzheng.preparedemo.network.volley;

/**
 * Created by yee on 7/1/14.
 *
 * @author yee
 */
public class Net {

    public static final String DATA = "data";
    public static final String MSG = "msg";
    public static final String STATUS = "status";


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
}
