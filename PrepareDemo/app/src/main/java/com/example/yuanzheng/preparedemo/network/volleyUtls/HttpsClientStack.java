package com.example.yuanzheng.preparedemo.network.volleyUtls;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by liangrenwang on 15/10/13.
 */
public class HttpsClientStack extends HurlStack {

    private OkHttpClient okHttpClient;
    private Map<String, SSLSocketFactory> socketFactoryMap;
    private String[] hosts;
    private int[] certResIds;
    private String certPass[];

    public HttpsClientStack(String[] certPass, int[] certResIds, String[] hosts) {
        this.certPass = certPass;
        this.certResIds = certResIds;
        this.hosts = hosts;
        socketFactoryMap = new HashMap<>(hosts.length);
        if (okHttpClient == null)
            okHttpClient = new OkHttpClient();
    }

    public HttpsClientStack(Map<String, SSLSocketFactory> socketFactoryMap) {
        this.socketFactoryMap = socketFactoryMap;
        if (okHttpClient == null)
            okHttpClient = new OkHttpClient();
    }

    public HttpsClientStack(OkHttpClient okHttpClient, Map<String, SSLSocketFactory> socketFactoryMap) {
        this.okHttpClient = okHttpClient;
        this.socketFactoryMap = socketFactoryMap;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {

        if ("https".equals(url.getProtocol()) && socketFactoryMap.containsKey(url.getHost())) {
            HttpsURLConnection connection = (HttpsURLConnection) new OkUrlFactory(okHttpClient).open(url);
            connection.setSSLSocketFactory(socketFactoryMap.get(url.getHost()));
            return connection;
        } else
            return new OkUrlFactory(okHttpClient).open(url);
    }

    public RequestQueue newRequestQueue(Context context) {
        RequestQueue requestQueue = null;
        try {
            for (int i = 0; i < certResIds.length; i++) {
                int res = certResIds[i];
                String password = certPass[i];
                SSLSocketFactory sslSocketFactory = createSSLSocketFactory(context, res, password);
                socketFactoryMap.put(hosts[i], sslSocketFactory);
            }
            requestQueue = Volley.newRequestQueue(context, this);
            requestQueue.start();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return requestQueue;
    }

    private SSLSocketFactory createSSLSocketFactory(Context context, int res, String password)
            throws CertificateException,
            NoSuchAlgorithmException,
            IOException,
            KeyStoreException,
            KeyManagementException {
        InputStream inputStream = context.getResources().openRawResource(res);
        KeyStore keyStore = KeyStore.getInstance("BKS");
        keyStore.load(inputStream, password.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
        return sslContext.getSocketFactory();
    }
}
