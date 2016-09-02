package com.example.baseproject.utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shivang Goel on 22/2/16.
 */
public class RetrofitUtils {

    /**
     * Private Instance Variable
     */
    private static RetrofitUtils classInstance = null;

    /**
     * Private Constructor to make this class singleton
     */
    private RetrofitUtils(){}

    /**
     * Method return the class instance
     * @return ToastUtils
     */
    public static RetrofitUtils getInstance(){
        if(classInstance == null){
            classInstance = new RetrofitUtils();
        }
        return classInstance;
    }

    /**
     * Method call to get Retrofit Builder
     * @param baseUrl
     * @return
     */
    public Retrofit getRetrofitBuilder(String baseUrl){
         return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param baseUrl REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public <T> T createRetrofitService(final Class<T> clazz, final String baseUrl) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T service = retrofit.create(clazz);

        return service;
    }

    /**
     * Method call to clean object from memory
     */
    public void cleanObject(){
        classInstance = null;
    }

}
