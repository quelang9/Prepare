package com.example.yuanzheng.preparedemo.widget;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.dachu.shop.R;
import com.dachu.shop.util.BasicUtil;
import com.dachu.shop.util.constant.ConstantUtils;

/**
 *
 */
public class TitleBuilder {

    private View viewTitle;
    private TextView tvTitle;
    private ImageView ivLeft;
    private ImageView ivRight;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvRightClear;

    public TitleBuilder(Activity context) {
        viewTitle = context.findViewById(R.id.titlebar);
        tvTitle = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
        ivLeft = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_left);
        ivRight = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_right);
        tvLeft = (TextView) viewTitle.findViewById(R.id.titlebar_tv_left);
        tvRight = (TextView) viewTitle.findViewById(R.id.titlebar_tv_right);
        tvRightClear= (TextView) viewTitle.findViewById(R.id.titlebar_clear);
    }

    public TitleBuilder(View context) {
        viewTitle = context.findViewById(R.id.titlebar);
        tvTitle = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
        ivLeft = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_left);
        ivRight = (ImageView) viewTitle.findViewById(R.id.titlebar_iv_right);
        tvLeft = (TextView) viewTitle.findViewById(R.id.titlebar_tv_left);
        tvRight = (TextView) viewTitle.findViewById(R.id.titlebar_tv_right);
        tvRightClear= (TextView) viewTitle.findViewById(R.id.titlebar_clear);
    }

    public TitleBuilder initCommonModule(final Activity context) {
        initNoLeftCommonModule(context).setLeftImage(R.drawable.back).setLeftText("返回").setLeftOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
        return this;
    }

    public TitleBuilder initNoLeftCommonModule(final Activity context) {
        setTitleBgRes(R.drawable.ab_solid_greentheme).setRightImage(R.drawable.phone).setRightText("客服")
                .setRightOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BasicUtil.dial(context, Uri.parse("tel:" + ConstantUtils.CUSTOMER_SERVICE_PHONE_NUMBER));
                    }
                });
        return this;
    }

    public TitleBuilder initNoLeftCommonModuleAndAddClear(final Activity context,String message,OnClickListener rightClearListener) {
        setTitleBgRes(R.drawable.ab_solid_greentheme).setRightClearText(message)
                .setRightClearOnClickListener(rightClearListener);
        return this;
    }

    public TitleBuilder initCommonModule(final Activity context,String message,OnClickListener rightClearListener){
        initNoLeftCommonModuleAndAddClear(context,message,rightClearListener).setLeftImage(R.drawable.back).setLeftText("返回").setLeftOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
        return this;
    }



    // title

    public TitleBuilder setTitleBgRes(int resid) {
        viewTitle.setBackgroundResource(resid);
        return this;
    }

    public TitleBuilder setTitleText(String text) {
        tvTitle.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        tvTitle.setText(text);
        return this;
    }

    // left

    public TitleBuilder setLeftImage(int resId) {
        ivLeft.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        ivLeft.setImageResource(resId);
        return this;
    }

    public TitleBuilder setLeftText(String text) {
        tvLeft.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        tvLeft.setText(text);
        return this;
    }

    public TitleBuilder setLeftOnClickListener(OnClickListener listener) {
        if (ivLeft.getVisibility() == View.VISIBLE) {
            ivLeft.setOnClickListener(listener);
        }
        if (tvLeft.getVisibility() == View.VISIBLE) {
            tvLeft.setOnClickListener(listener);
        }

        return this;
    }

    // right

    public TitleBuilder setRightImage(int resId) {
        ivRight.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        ivRight.setImageResource(resId);
        return this;
    }

    public TitleBuilder setRightText(String text) {
        tvRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        tvRight.setText(text);
        return this;
    }
    public TitleBuilder setRightClearText(String text) {
        tvRightClear.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        tvRightClear.setText(text);
        return this;
    }

    public TitleBuilder setRightOnClickListener(OnClickListener listener) {
        if (ivRight.getVisibility() == View.VISIBLE) {
            ivRight.setOnClickListener(listener);
        }
        if (tvRight.getVisibility() == View.VISIBLE) {
            tvRight.setOnClickListener(listener);
        }
        return this;
    }
    public TitleBuilder setRightClearOnClickListener(OnClickListener listener) {
        if (tvRightClear.getVisibility() == View.VISIBLE) {
            tvRightClear.setOnClickListener(listener);
        }
        return this;
    }

    public View build() {
        return viewTitle;
    }

}
