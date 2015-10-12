package com.example.yuanzheng.preparedemo.network.volley;

import android.text.TextUtils;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by yee on 7/2/14.
 *
 * @author yee
 */
public class CommonNetListener implements NetListener {

    private NetListener mListener;

    public CommonNetListener(NetListener netListener) {
        mListener = netListener;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (null != mListener) {
            if (null == error || TextUtils.isEmpty(error.getMessage())) {
                error = new VolleyError("服务器异常");
            }
            mListener.onErrorResponse(error);
        }
    }

    @Override
    public void onResponse(Object response) {
        if (null == response) {
            onErrorResponse(new ResponseError("服务器无响应"));
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(response.toString());
            int code = jsonObject.optInt(Net.STATUS, 1);
            if (code != Net.SUCCESS) {
                String msg = jsonObject.optString(Net.MSG, "服务器数据异常");
                onErrorResponse(new ResponseError(msg));
                if (code == Net.NEED_LOGIN) {
//                    EventBus.getDefault().post(new LogoutEvent());
                } else if (code == Net.NEED_RE_INIT||code==Net.INIT_KEY_ILLEGAL) {
//                    EventBus.getDefault().post(new ReInitEvent(msg));
                }
                return;
            }
            if (null != mListener) {
                mListener.onResponse(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            onErrorResponse(new ResponseError("网络解析出错"));
        }
    }
}
