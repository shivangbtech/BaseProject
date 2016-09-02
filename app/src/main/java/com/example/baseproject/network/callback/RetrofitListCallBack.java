package com.example.baseproject.network.callback;

import com.example.baseproject.models.base.BaseListResponse;
import com.example.baseproject.models.base.BaseResponse;
import com.example.baseproject.network.NetworkConstant;
import com.example.baseproject.network.helper.NetworkHelper;
import com.example.baseproject.network.ResponseStatus;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shivang Goel on 26/02/16.
 */
public abstract class RetrofitListCallBack<T extends BaseResponse> implements Callback<List<T>> {

    @Override
    public void onResponse(Call<List<T>> call, Response<List<T>> response) {
        BaseListResponse<T> baseListResponse = new BaseListResponse<>();
        if (response.isSuccessful()) {
            baseListResponse.setResponseStatus(ResponseStatus.OK);
        } else {
            baseListResponse.setResponseStatus(ResponseStatus.SERVER_ERROR);
            baseListResponse.setError(NetworkHelper.getClassInstance().getDefaultError(NetworkConstant.ERROR_TYPE_SERVER));
        }
        baseListResponse.setList(response.body());
        handleResponse(call, baseListResponse);
    }

    @Override
    public void onFailure(Call<List<T>> call, Throwable throwable) {
        BaseListResponse<T> baseListResponse = new BaseListResponse<>();
        if (throwable instanceof IOException) {
            baseListResponse.setResponseStatus(ResponseStatus.NETWORK_ERROR);
            baseListResponse.setError(NetworkHelper.getClassInstance().getDefaultError(NetworkConstant.ERROR_TYPE_NETWORK));
        } else {
            baseListResponse.setResponseStatus(ResponseStatus.UNEXPECTED);
            baseListResponse.setError(NetworkHelper.getClassInstance().getDefaultError(NetworkConstant.ERROR_TYPE_UNEXPECTED));
        }
        handleResponse(call, baseListResponse);
    }

    public abstract void handleResponse(Call<List<T>> call, BaseListResponse<T> response);
}
