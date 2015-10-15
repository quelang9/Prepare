package com.example.yuanzheng.preparedemo.network.volleyUtls;

/**
 * Created by liangrenwang on 15/10/14.
 */
public class BaseEntity {
    public int status;
    public String msg = "";
    public CacheEntity cache ;
    public Object data;

   public class CacheEntity{
        public String type = "";
        public long expire;
    }
}
