package com.example.baseproject.network.callback;

import com.example.baseproject.models.base.BaseResponse;
import com.example.baseproject.network.NetworkConstant;
import com.example.baseproject.network.helper.NetworkHelper;
import com.example.baseproject.network.ResponseStatus;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shivang Goel on 20/02/16.
 */
public abstract class RetrofitCallBack<T extends BaseResponse> implements Callback<T> {

    private T t;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body().getErrorCode() == NetworkConstant.RESPONSE_ERROR_CODE_SUCCESS)
                response.body().setResponseStatus(ResponseStatus.OK);
            else
                response.body().setResponseStatus(ResponseStatus.SERVER_ERROR);

            handleResponse(call, response.body());
        } else {
            t = getInstance();
            t.setResponseStatus(ResponseStatus.SERVER_ERROR);
            t.setError(NetworkHelper.getClassInstance().getDefaultError(NetworkConstant.ERROR_TYPE_SERVER));
            handleResponse(call, t);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        t = getInstance();
        if (throwable instanceof IOException) {
            t.setResponseStatus(ResponseStatus.NETWORK_ERROR);
            t.setError(NetworkHelper.getClassInstance().getDefaultError(NetworkConstant.ERROR_TYPE_NETWORK));
        } else {
            t.setResponseStatus(ResponseStatus.UNEXPECTED);
            t.setError(NetworkHelper.getClassInstance().getDefaultError(NetworkConstant.ERROR_TYPE_UNEXPECTED));
        }
        handleResponse(call, t);
    }

    private T getInstance() {
        try {
            t = (T) ((Class) ((ParameterizedType) this.getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException e) {
            t = null;
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            t = null;
            e.printStackTrace();
        }
        return t;
    }

    public abstract void handleResponse(Call<T> call, T t);
}
