package com.example.yuanzheng.preparedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yuanzheng.preparedemo.network.protocal.RequestParamUtils;
import com.example.yuanzheng.preparedemo.utils.logger.LogUtil;
import com.google.gson.Gson;

import org.androidannotations.annotations.EActivity;

import java.util.LinkedHashMap;
import java.util.Map;
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map<String,String> map=new LinkedHashMap<String,String>();
        map.put("name", "yuanzheng");

        Map<String,String> mParams=RequestParamUtils.getInstance().
                setCallParams("www.baidu.com", "1").
                addDataJson(new Gson().toJson(map)).
                completeFinalParams();
        LogUtil.t("TestProtocalActivity").json(mParams.toString());
    }

}
