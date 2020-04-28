package com.pool.http;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class PoolHttpUtil {

    private PoolingHttpClientConnectionManager connMgr;

    private RequestConfig requestConfig;

    private  CloseableHttpClient httpclient;

    private String CHARSET = "UTF-8";

    protected PoolHttpUtil(PoolingHttpClientConnectionManager connMgr, RequestConfig requestConfig){
        this.connMgr=connMgr;
        this.requestConfig=requestConfig;
    }

    private void init() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLConnectionSocketFactory sslConnSocketFactory = new SSLConnectionSocketFactory(createIgnoreVerifySSL());
        httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslConnSocketFactory)
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    private SSLContext createIgnoreVerifySSL() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        SSLContext sc = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                return true;
            }
        }).build();
        return sc;
    }

    public  String httpPostEx(String url, String data, String contentType) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpPost httpPost = null;
        try {
            init();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type",contentType);
            httpPost.setEntity(new StringEntity(data, CHARSET));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new IOException("post request fail");
            }
            return EntityUtils.toString(response.getEntity(), CHARSET);
        }finally {
            if(httpPost!=null){
                httpPost.releaseConnection();
            }
        }
    }

}
