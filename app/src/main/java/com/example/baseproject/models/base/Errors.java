package com.example.baseproject.models.base;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivang Goel on 26/4/16.
 */
public class Errors {

    @SerializedName("errType")
    private String errType;

    @SerializedName("messages")
    private List<ErrorMessage> messages;

    public Errors() {
        errType = "";
        messages = new ArrayList<>();
    }


    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    public void setMessages(ErrorMessage messages) {
        this.messages.add(messages);
    }

    public ErrorMessage getMessages(int index) {
        return messages.get(index);
    }

    @Override
    public String toString() {
        StringBuffer error = new StringBuffer();
        error.append(errType + " :");
        for (ErrorMessage st : messages) {
            error.append(" " + messages.toString() + "; ");
        }
        return error.toString();
    }
}
