package com.example.baseproject.listeners;

/**
 * Created by Shivang Goel on 13/4/16.
 */
public interface PermissionErrorListener {

    void isAlreadyGranted();

    void BeforeMarshmallow();

    void onError(String errorMessage);
}
