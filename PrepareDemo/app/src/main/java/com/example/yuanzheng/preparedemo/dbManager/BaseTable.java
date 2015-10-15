package com.example.yuanzheng.preparedemo.dbManager;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by liangrenwang on 15/10/14.
 */
@Table(name="BaseTable")
public class BaseTable extends Model{
    @Column(name = "apiName")
    public String apiName;
    @Column(name = "cacheType")
    public String cacheType;
    @Column(name = "expire")
    public long expire;
    @Column(name = "starttime")
    public long starttime;
    @Column(name = "data")
    public String data;

    @Override
    public String toString() {
        return "BaseTable{" +
                "apiName='" + apiName + '\'' +
                ", cacheType='" + cacheType + '\'' +
                ", expire=" + expire +
                ", starttime=" + starttime +
                ", data='" + data + '\'' +
                '}';
    }
}
