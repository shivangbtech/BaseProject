package com.example.baseproject.network.helper;

import com.example.baseproject.models.base.ErrorMessage;
import com.example.baseproject.models.base.Errors;
import com.example.baseproject.network.NetworkConstant;

/**
 * Created by Shivang Goel on 21/7/16.
 */

public class NetworkHelper {

    private static NetworkHelper mClassInstance = null;

    private NetworkHelper() {
    }

    public static NetworkHelper getClassInstance() {
        if (mClassInstance == null)
            mClassInstance = new NetworkHelper();
        return mClassInstance;
    }

    public ErrorMessage getDefaultErrorMessage(String errorType) {
        ErrorMessage errorMessage = new ErrorMessage();
        switch (errorType) {
            case NetworkConstant.ERROR_TYPE_SERVER: {
                errorMessage.setErrorId(NetworkConstant.ERROR_MESSAGE_ID_SERVER);
                errorMessage.setErrorMessage(NetworkConstant.ERROR_MESSAGE_SERVER);
                break;
            }
            case NetworkConstant.ERROR_TYPE_NETWORK: {
                errorMessage.setErrorId(NetworkConstant.ERROR_MESSAGE_ID_NETWORK);
                errorMessage.setErrorMessage(NetworkConstant.ERROR_MESSAGE_NETWORK);
                break;
            }
            default: {
                errorMessage.setErrorId(NetworkConstant.ERROR_MESSAGE_ID_UNEXPECTED);
                errorMessage.setErrorMessage(NetworkConstant.ERROR_MESSAGE_UNEXPECTED);
                break;
            }
        }
        return errorMessage;
    }

    public Errors getDefaultError(String errorType) {
        Errors errors = new Errors();

        switch (errorType) {
            case NetworkConstant.ERROR_TYPE_SERVER: {
                errors.setErrType(NetworkConstant.ERROR_TYPE_SERVER);
                errors.setMessages(getDefaultErrorMessage(NetworkConstant.ERROR_TYPE_SERVER));
                break;
            }
            case NetworkConstant.ERROR_TYPE_NETWORK: {
                errors.setErrType(NetworkConstant.ERROR_TYPE_NETWORK);
                errors.setMessages(getDefaultErrorMessage(NetworkConstant.ERROR_TYPE_NETWORK));
                break;
            }
            default: {
                errors.setErrType(NetworkConstant.ERROR_TYPE_UNEXPECTED);
                errors.setMessages(getDefaultErrorMessage(NetworkConstant.ERROR_TYPE_UNEXPECTED));
                break;
            }
        }
        return errors;
    }

    public void cleanObject() {
        mClassInstance = null;
    }
}
