package com.example.baseproject.database;

import android.content.Context;

import com.example.baseproject.variables.GlobalParams;


/**
 * Created by Shivang Goel on 27/6/16.
 */


public class AppPreference {


    public String getLoginStatus(Context context){
        return PersistenceStore.getInstance().LoadPreferences(context, GlobalParams.PARAM_IS_LOGED_IN, "NO");
    }

    public void setLoginStatus(Context context, String value){
        PersistenceStore.getInstance().SavePreferences(context, GlobalParams.PARAM_IS_LOGED_IN, value);
    }

    public void setLoginId(Context context, String value){
        PersistenceStore.getInstance().SavePreferences(context, GlobalParams.PARAM_LOGIN_ID, value);
    }

    public String getLoginId(Context context){
        return PersistenceStore.getInstance().LoadPreferences(context, GlobalParams.PARAM_LOGIN_ID, "");
    }

    public String getPassword(Context context){
        return PersistenceStore.getInstance().LoadPreferences(context, GlobalParams.PARAM_PASSWORD, "");
    }

    public void setPassword(Context context, String value){
        PersistenceStore.getInstance().SavePreferences(context, GlobalParams.PARAM_PASSWORD, value);
    }

}
