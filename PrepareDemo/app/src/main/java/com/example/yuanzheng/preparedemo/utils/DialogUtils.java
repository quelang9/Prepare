package com.example.yuanzheng.preparedemo.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.yuanzheng.preparedemo.widget.CustomProgressDialog;

/**
 * Created by yuanzheng on 15/6/13.
 */
public class DialogUtils {
    private static CustomProgressDialog progressDialog = null;

    public static void startProgressDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("加载中...");
        }
        progressDialog.show();
    }

    public static void stopProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * show a dialog with a simple confirm button
     *
     * @param activity activity
     */
    public static void showInfoDialog(Activity activity, String title, String message) {
        if (null == activity) {
            return;
        }
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            return;
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.title(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.content(message);
        }
        builder.negativeText("确认");
        builder.show();
    }

    /**
     * show a dialog with a simple confirm button
     *
     * @param activity activity
     */
    public static void showInfoDialog(Activity activity, String title, String message, String button, MaterialDialog.ButtonCallback callback) {
        if (null == activity || null == button) {
            return;
        }
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            return;
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.title(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.content(message);
        }
        builder.negativeText(button);
        builder.callback(callback);
        builder.show();
    }

    /**
     * show a dialog with two buttons
     *
     * @param activity activity
     */
    public static void showInfoDialog(Activity activity, String title, String message, String buttonPositive, String buttonNegative, MaterialDialog.ButtonCallback callback) {
        if (null == activity || null == buttonNegative || null == buttonPositive) {
            return;
        }
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            return;
        }
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.title(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.content(message);
        }
        builder.negativeText(buttonNegative);
        builder.positiveText(buttonPositive);
        builder.callback(callback);
        builder.show();
    }
}
