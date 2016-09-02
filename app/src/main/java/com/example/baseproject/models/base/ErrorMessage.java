package com.example.baseproject.models.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shivang Goel on 26/4/16.
 */
public class ErrorMessage {

    @SerializedName("id")
    private long errorId;

    @SerializedName("message")
    private String errorMessage;

    public ErrorMessage() {
        this.errorId = -1;
        this.errorMessage = "";
    }

    public long getErrorId() {
        return errorId;
    }

    public void setErrorId(long errorId) {
        this.errorId = errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error_Id = " + errorId + "Error Message = " + errorMessage;
    }
}
