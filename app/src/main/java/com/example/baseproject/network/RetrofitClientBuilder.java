package com.example.baseproject.network;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Shivang Goel on 13/7/16.
 */

public class RetrofitClientBuilder {

    private static final String KEYSTORE_PASSWORD = "123456";
    private static final int NO_TIMEOUT = -1;

    private int connectionTimeout = NO_TIMEOUT;
    private TimeUnit connectionTimeoutTimeUnit;
    private int readTimeout = NO_TIMEOUT;
    private TimeUnit readTimeoutTimeUnit;
    private int writeTimeout = NO_TIMEOUT;
    private TimeUnit writeTimeoutTimeUnit;
    private Cache cache;
    private List<Interceptor> interceptorList = new ArrayList<>();
    private List<Interceptor> networkInterceptorList = new ArrayList<>();
    private SSLSocketFactory sSLSocketFactory;
    private HostnameVerifier hostnameVerifier;

    public RetrofitClientBuilder() {
        this(new Builder());
    }

    private RetrofitClientBuilder(Builder builder) {

        this.connectionTimeout = builder.connectionTimeout;
        this.connectionTimeoutTimeUnit = builder.connectionTimeoutTimeUnit;
        this.readTimeout = builder.readTimeout;
        this.readTimeoutTimeUnit = builder.readTimeoutTimeUnit;
        this.cache = builder.cache;
        this.interceptorList = builder.interceptorList;
        this.networkInterceptorList = builder.networkInterceptorList;
        this.sSLSocketFactory = builder.sSLSocketFactory;
        this.hostnameVerifier = builder.hostnameVerifier;

    }

    public final static class Builder {

        private int connectionTimeout = NO_TIMEOUT;
        private TimeUnit connectionTimeoutTimeUnit;
        private int readTimeout = NO_TIMEOUT;
        private TimeUnit readTimeoutTimeUnit;
        private int writeTimeout = NO_TIMEOUT;
        private TimeUnit writeTimeoutTimeUnit;
        private Cache cache;
        private List<Interceptor> interceptorList = new ArrayList<>();
        private List<Interceptor> networkInterceptorList = new ArrayList<>();
        private SSLSocketFactory sSLSocketFactory;
        private HostnameVerifier hostnameVerifier;

        public Builder setConnectionTimeout(int connectionTimeout, TimeUnit timeUnit) {
            this.connectionTimeout = connectionTimeout;
            this.connectionTimeoutTimeUnit = timeUnit;
            return this;
        }

        public Builder setReadTimeout(int readTimeout, TimeUnit timeUnit) {
            this.readTimeout = readTimeout;
            this.readTimeoutTimeUnit = timeUnit;
            return this;
        }

        public Builder setWriteTimeout(int writeTimeout, TimeUnit timeUnit) {
            this.writeTimeout = writeTimeout;
            this.writeTimeoutTimeUnit = timeUnit;
            return this;
        }

        public Builder setCache(Cache cache) {
            this.cache = cache;
            return this;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            networkInterceptorList.add(interceptor);
            return this;
        }

        public Builder addNetworkInterceptors(List<Interceptor> interceptors) {
            networkInterceptorList.addAll(interceptors);
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            interceptorList.add(interceptor);
            return this;
        }

        public Builder addInterceptors(List<Interceptor> interceptors) {
            interceptorList.addAll(interceptors);
            return this;
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) throw new NullPointerException("hostnameVerifier == null");
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public Builder hostnameVerifierDefault() {
            this.hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            return this;
        }

        public Builder pinCertificates() throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException,
                KeyManagementException, UnrecoverableKeyException, NullPointerException {

            InputStream inputStream = this.getClass().getResourceAsStream("/keystore.bks");
            KeyStore trusted = KeyStore.getInstance("BKS"); // HttpClientBuilder.BOUNCY_CASTLE
            trusted.load(inputStream, KEYSTORE_PASSWORD.toCharArray());

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trusted);

            KeyManagerFactory keyManagerFactory =
                    KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(trusted, KEYSTORE_PASSWORD.toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS"); // SSLSocketFactory.TLS
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            sSLSocketFactory = sslContext.getSocketFactory();

            return this;
        }

        public Builder ignoreCertificates() /*throws NoSuchAlgorithmException, KeyManagementException*/ {
            X509TrustManager easyTrustManager = new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            };

            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{
                    easyTrustManager
            };

            // Install the all-trusting trust manager
            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("TLS");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                sc.init(null, trustAllCerts, new SecureRandom());
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            sSLSocketFactory = sc.getSocketFactory();

            return this;
        }

        public RetrofitClientBuilder build() {
            return new RetrofitClientBuilder(this);
        }
    }

    public OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (connectionTimeout != NO_TIMEOUT) {
            builder.connectTimeout(connectionTimeout, connectionTimeoutTimeUnit);
        }
        if (readTimeout != NO_TIMEOUT) {
            builder.readTimeout(readTimeout, readTimeoutTimeUnit);
        }
        if (cache != null) {
            builder.cache(cache);
        }
        for (Interceptor interceptor : networkInterceptorList) {
            builder.addNetworkInterceptor(interceptor);
        }
        for (Interceptor interceptor : interceptorList) {
            builder.addInterceptor(interceptor);
        }
        if (sSLSocketFactory != null) {
            builder.sslSocketFactory(sSLSocketFactory);
        }
        if(hostnameVerifier != null){
            builder.hostnameVerifier(hostnameVerifier);
        }
        if(writeTimeout != NO_TIMEOUT){
            builder.writeTimeout(writeTimeout, writeTimeoutTimeUnit);
        }
        return builder.build();
    }
}
