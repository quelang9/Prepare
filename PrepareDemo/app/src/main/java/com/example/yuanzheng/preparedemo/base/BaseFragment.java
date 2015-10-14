package com.example.yuanzheng.preparedemo.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;


import com.example.yuanzheng.preparedemo.utils.ToastUtils;
import com.example.yuanzheng.preparedemo.widget.CustomProgressDialog;

import de.greenrobot.event.EventBus;

/**
 * Created by yuanzheng on 15/5/30.
 */
public class BaseFragment extends Fragment {
    private CustomProgressDialog progressDialog = null;
    protected int mStartTime;
    protected void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(getActivity());
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("加载中...");
        }

        progressDialog.show();
    }

    protected void stopProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }


    /**
     * 描述：Toast提示文本.
     *
     * @param text 文本
     */
    public void showToast(String text) {
        ToastUtils.show(getActivity(), text);
    }

    /**
     * 描述：Toast提示文本.
     *
     * @param resId 文本的资源ID
     */
    public void showToast(int resId) {
        ToastUtils.show(getActivity(), resId);
    }

}
