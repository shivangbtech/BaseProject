package com.example.baseproject.network;

/**
 * Created by Shivang Goel on 21/7/16.
 */


public class NetworkConstant {

    // Base URL
    public static final String baseUrl = "";


    // Error Codes
    public static final int RESPONSE_ERROR_CODE_SUCCESS = 200;
    public static final int RESPONSE_ERROR_CODE_UNEXPECTED = 000;

    // Error ids
    public static final long ERROR_MESSAGE_ID_UNEXPECTED = 0000;
    public static final long ERROR_MESSAGE_ID_SERVER = 1111;
    public static final long ERROR_MESSAGE_ID_NETWORK = 2222;

    // Error Types
    public static final String ERROR_TYPE_UNEXPECTED = "UNEXPECTED";
    public static final String ERROR_TYPE_SERVER = "SERVER";
    public static final String ERROR_TYPE_NETWORK = "NETWORK";

    // Error Messages
    public static final String ERROR_MESSAGE_UNEXPECTED = "Ouch !!!! Unexpected occurred ";
    public static final String ERROR_MESSAGE_SERVER = "Ouch !!!! Server Error Occurred ";
    public static final String ERROR_MESSAGE_NETWORK = "Ouch !!!! Network Error Occurred ";
}
