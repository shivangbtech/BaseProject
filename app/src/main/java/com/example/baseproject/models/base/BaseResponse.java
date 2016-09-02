package com.example.baseproject.models.base;

import com.example.baseproject.network.ResponseStatus;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shivang Goel on 26/4/16.
 */
public class BaseResponse {

    private ResponseStatus responseStatus;

    @SerializedName("code")
    private int errorCode;

    @SerializedName("error")
    private Errors error;

    public BaseResponse() {
        errorCode = -1;
        error = new Errors();
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /*public String getConsolidatedErrorMsg() {
        String error = "";
        if (getError() != null && getError().getMessages() != null)
            for (int i = 0; i < getError().getMessages().size(); i++) {
                error = error + ", " + getError().getMessages().get(i).getMsg();
            }
        if (!error.isEmpty())
            return error.substring(2, error.length());
        else
            return error;
    }*/

    @Override
    public String toString() {
        StringBuilder error = new StringBuilder();
        error.append(errorCode + " : ");
        error.append(error.toString());
        return error.toString();
    }
}
