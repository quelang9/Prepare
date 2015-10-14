package com.example.yuanzheng.preparedemo.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.example.yuanzheng.preparedemo.MainActivity;
import com.example.yuanzheng.preparedemo.R;
import com.example.yuanzheng.preparedemo.utils.logger.LogUtil;
import com.example.yuanzheng.preparedemo.widget.TitleBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yefeng on 6/11/15.
 * github:yefengfreedom
 */
@EActivity(R.layout.activity_web)
public class BaseWebViewActivity extends BaseActivity {
    private static final String TAG=BaseWebViewActivity.class.getName();
    @Extra
    protected String mUrl;
    @Extra
    protected String mTitle;
    protected TitleBuilder mTitleBuilder;
    @ViewById(R.id.web_view)
    WebView mWebView;
    @ViewById(R.id.web_view_top_progress)
    ProgressBar mTopProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);
    }

    @AfterViews
    void init() {
//        mTitleBuilder = new TitleBuilder(this).setTitleText("").initCommonModule(this);
        initView();
    }

    void initView() {
        WebSettings settings = mWebView.getSettings();

        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitleBuilder.setTitleText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    hideTopProgress();
                    return;
                }
                if (mTopProgress != null) {
                    mTopProgress.setProgress(newProgress);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("提示");
                builder.setMessage(message);
                builder.setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        }
                );
                builder.setCancelable(false);
                builder.create();
                try {
                    builder.show();
                } catch (Exception ex) {
                    LogUtil.t(TAG).e(ex.toString());
                }
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message,
                                       final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("提示");
                builder.setMessage(message);
                builder.setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        }
                );
                builder.setNeutralButton(android.R.string.cancel,
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        }
                );
                builder.setCancelable(false);
                builder.create();
                try {
                    builder.show();
                } catch (Exception ex) {
                    LogUtil.t(TAG).e(ex.toString());
                }
                return true;

            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, final JsPromptResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("提示");
                builder.setMessage(message);
                final EditText editText = new EditText(view.getContext());
                editText.setText(defaultValue);
                builder.setView(editText);
                builder.setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm(editText.getText().toString());
                            }
                        }
                );
                builder.setNeutralButton(android.R.string.cancel,
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        }
                );
                builder.setCancelable(false);
                builder.create();
                try {
                    builder.show();
                } catch (Exception ex) {
                    LogUtil.t(TAG).e(ex.toString());
                }
                return true;
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(mUrl);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mUrl = url;
                if (mTopProgress != null) {
                    mTopProgress.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mUrl = url;
                hideTopProgress();
            }
        });
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);

        String url = getIntent().getStringExtra("url");

        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)) {
            mTitle = title;
        }
        mTitleBuilder.setTitleText(mTitle);
        if (!TextUtils.isEmpty(url)) {
            mUrl = url;
        }
        mWebView.loadUrl(mUrl);
    }

    protected void hideTopProgress() {
        if (mTopProgress != null) {
            mTopProgress.setVisibility(View.GONE);
        }
    }

    protected void loadUrl(final String url) {
        if (null != mWebView) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    if (mWebView != null) {
                        mWebView.loadUrl(url);
                    }
                }
            });
        }
    }

    protected void postUrl(final String url, final byte[] data) {
        if (null != mWebView) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    if (null != mWebView) {
                        mWebView.postUrl(url, data);
                    }
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", mUrl);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String url = savedInstanceState.getString("url");
        if (!TextUtils.isEmpty(url) && null != mWebView) {
            loadUrl(url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && null != mWebView && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
        return true;
    }
}