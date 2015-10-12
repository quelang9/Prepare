package com.example.yuanzheng.preparedemo.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;


/**
 * Created by yefeng on 6/15/15.
 * github:yefengfreedom
 */
public class ImageUtils {

    /**
     * 按照指定宽度加载网络图片
     *
     * @param imageView   image view
     * @param url         url
     * @param mBitmapUtils image bitmapUtils
     * @param width       指定宽度
     */
//    public static void loadNetImageByWidth(final ImageView imageView, String url, SyncBitmapUtils mBitmapUtils, final int width) {
//        if (TextUtils.isEmpty(url) || width <= 0 || null == mBitmapUtils || null == imageView) {
//            return;
//        }
//
//        mBitmapUtils.getBitmapUtils().display(imageView, url, new BitmapLoadCallBack<ImageView>() {
//            @Override
//            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
//                imageView.setImageBitmap(zoomBitmapByWidth(bitmap, AppInfo.width));
//            }
//
//            @Override
//            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
//
//            }
//        });
//
//    }

    /**
     * 按照指定宽度缩放图片
     *
     * @param bitmap 原始图片
     * @param width  指定宽度
     * @return 缩放后的图片
     */
    public static Bitmap zoomBitmapByWidth(Bitmap bitmap, int width) {
        if (width <= 0 || null == bitmap) {
            return bitmap;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float scale = (float) width / (float) w;
        if (scale <= 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale); //长和宽放大缩小的比例
        try {
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
        return bitmap;
    }

}
