package com.example.yuanzheng.preparedemo.dbManager;


import com.example.yuanzheng.preparedemo.network.volleyUtls.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liangrenwang on 15/10/14.
 */
public class CacheUtils {

    private final Map<String,BaseTable> cacheList;

    public static final CacheUtils instances = new CacheUtils();

    public static  CacheUtils getInstances() {
        return instances;
    }

    private CacheUtils() {
        this.cacheList = new HashMap<>();
    }

    public BaseTable getValue(String key){
        BaseTable baseTable = null;
        if(cacheList.containsKey(key)&&cacheList.get(key)!=null){
            baseTable = cacheList.get(key);
        }else{
            baseTable = DbUtils.queryDataByApi(key);
            cacheList.put(key,baseTable);
        }
        if(baseTable==null)
            baseTable = new BaseTable();
        return baseTable;
    }
    public void updateValue(String key,BaseEntity entity){
        if(entity!=null&&entity.cache!=null) {
            BaseTable baseTable = new BaseTable();
            baseTable.cacheType = entity.cache.type;
            baseTable.expire = entity.cache.expire;
            baseTable.apiName = key;
            baseTable.data = (String) entity.data;
            baseTable.starttime = System.currentTimeMillis() / 1000;
            cacheList.put(key, baseTable);
            DbUtils.saveAndUpdateDataByApi(key, entity);
        }
    }

    public void clearAll(){
        cacheList.clear();
    }

//    public boolean isUpdate(String key) {
//        BaseTable baseTable = getValue(key);
//        if (NetStatus.CACHE_LOCAL.equals(baseTable.cacheType)) {
//            long currentTime = System.currentTimeMillis() / 1000;
//            if (currentTime - baseTable.starttime >= baseTable.expire) {
//                return true;
//            }
//        } else if (NetStatus.CACHE_QUERY.equals(baseTable.cacheType)) {
//            return true;
//        }
//        return false;
//    }
//    public boolean isUpdate(BaseTable baseTable) {
//        if (NetStatus.CACHE_LOCAL.equals(baseTable.cacheType)) {
//            long currentTime = System.currentTimeMillis() / 1000;
//            if (currentTime - baseTable.starttime >= baseTable.expire) {
//                return true;
//            }
//        } else if (NetStatus.CACHE_QUERY.equals(baseTable.cacheType)) {
//            return true;
//        }
//        return false;
//    }
}
