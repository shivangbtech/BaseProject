package com.example.baseproject.models.event;

/**
 * Created by Shivang Goel on 17/2/16.
 */
public class MessageEvent {

    public static int STATUS_SUCCESS = 0;
    public static int STATUS_FAILURE = 1;
    private String message;
    private int requestCode;
    private int status;

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(int requestCode, String message) {
        this.message = message;
        this.requestCode = requestCode;
    }

    public MessageEvent(int requestCode, int status, String message) {
        this.status = status;
        this.message = message;
        this.requestCode = requestCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
