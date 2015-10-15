package com.example.yuanzheng.preparedemo.dbManager;

import android.text.TextUtils;
import android.util.Log;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.example.yuanzheng.preparedemo.network.volleyUtls.BaseEntity;
import com.example.yuanzheng.preparedemo.network.volleyUtls.NetStatus;


/**
 * Created by liangrenwang on 15/10/14.
 */
public class DbUtils {


    public static boolean isUpdate(String api) {
        BaseTable baseTable = queryDataByApi(api);
        if (NetStatus.CACHE_LOCAL.equals(baseTable.cacheType)) {
            long currentTime = System.currentTimeMillis() / 1000;
            if (currentTime - baseTable.starttime >= baseTable.expire) {
                return true;
            }
        } else if (NetStatus.CACHE_QUERY.equals(baseTable.cacheType)) {
            return true;
        }
        return false;
    }

    public static BaseTable queryDataByApi(String api) {
        return new Select().from(BaseTable.class).where("apiName = ?", api).executeSingle();
    }



    public static void saveAndUpdateDataByApi(String api, BaseEntity entity) {
        BaseTable baseTable = queryDataByApi(api);
        if (baseTable == null) {
            baseTable = new BaseTable();
            baseTable.apiName = api;
            baseTable.cacheType = entity.cache.type;
            baseTable.expire = entity.cache.expire;
            baseTable.starttime = System.currentTimeMillis() / 1000;
            baseTable.data =  entity.data.toString();
            baseTable.save();
        } else {
            updateDataByApi(api, entity);
        }
    }

    public static void updateDataByApi(String api, BaseEntity entity) {

        if(NetStatus.CACHE_UNMODIFIED.equals(entity.cache.type)){
            new Update(BaseTable.class).set("cacheType = ?,expire = ?,starttime = ?",
                    entity.cache.type,
                    entity.cache.expire,
                    System.currentTimeMillis() / 1000)
                    .where("apiName = ?", api).execute();
        }else {
            new Update(BaseTable.class).set("cacheType = ?,expire = ?,starttime = ?,data = ?",
                    entity.cache.type,
                    entity.cache.expire,
                    System.currentTimeMillis() / 1000,
                    entity.data)
                    .where("apiName = ?", api).execute();
        }
    }

    public static void deleteDataByApi(String api){
        new Delete().from(BaseTable.class).where(" apiName = ?",api).execute();
    }
}
