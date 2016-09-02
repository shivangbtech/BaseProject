package com.example.baseproject.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shivang Goel on 22/7/16.
 */

public class RetrofitClient {

    private static RetrofitClient mClassInstance = null;

    private RetrofitClient() {
    }

    public RetrofitClient getInstance() {
        if (mClassInstance == null)
            mClassInstance = new RetrofitClient();
        return mClassInstance;
    }

    public Retrofit getRetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       /* OkHttpClient client = new OkHttpClient.Builder()
                                        .readTimeout(2, TimeUnit.MINUTES)
                                        .connectTimeout(2, TimeUnit.MINUTES)
                                        .addInterceptor(interceptor).build();*/

        RetrofitClientBuilder clientBuilder = new RetrofitClientBuilder.Builder()
                .setReadTimeout(2, TimeUnit.MINUTES)
                .setConnectionTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .ignoreCertificates()
                .hostnameVerifierDefault()
                .build();

        return new Retrofit.Builder()
                .baseUrl(NetworkConstant.baseUrl)
                .client(clientBuilder.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofitClient(final HashMap<String, String> requestHeaderMap) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       /* OkHttpClient client = new OkHttpClient.Builder()
                                        .readTimeout(2, TimeUnit.MINUTES)
                                        .connectTimeout(2, TimeUnit.MINUTES)
                                        .addInterceptor(interceptor).build();*/

        RetrofitClientBuilder clientBuilder = new RetrofitClientBuilder.Builder()
                .setReadTimeout(2, TimeUnit.MINUTES)
                .setConnectionTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        Set<Map.Entry<String, String>> entrySet = requestHeaderMap.entrySet();
                        for (Map.Entry<String, String> entry : entrySet) {
                            if (entry.getValue().isEmpty())
                                builder.removeHeader(entry.getKey());
                            else
                                builder.addHeader(entry.getKey(), entry.getValue());
                        }
                        Request request = builder.build();
                        return chain.proceed(request);
                    }
                })
                .ignoreCertificates()
                .hostnameVerifierDefault()
                .build();

        return new Retrofit.Builder()
                .baseUrl(NetworkConstant.baseUrl)
                .client(clientBuilder.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <S> S getClient(Class<S> serviceClass) {
        return getRetrofitClient().create(serviceClass);
    }

    public <S> S getClient(Class<S> serviceClass, final HashMap<String, String> requestHeaderMap) {
        return getRetrofitClient(requestHeaderMap).create(serviceClass);
    }

    // http://stackoverflow.com/questions/36579762/how-to-set-stale-time-for-okhttp-android
    public final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };
}
