package com.example.yuanzheng.preparedemo.dbManager;

import android.os.AsyncTask;

import com.example.yuanzheng.preparedemo.network.volleyUtls.BaseEntity;


/**
 * Created by liangrenwang on 15/10/15.
 */
public class AsyncDbManager extends AsyncTask<Object,Integer,Boolean> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        String api = (String)params[0];
        BaseEntity baseEntity = (BaseEntity) params[1];
        if(baseEntity!=null)
        DbUtils.saveAndUpdateDataByApi(api,baseEntity);
        return true;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

}
