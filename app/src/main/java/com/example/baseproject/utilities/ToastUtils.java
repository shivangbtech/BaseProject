package com.example.baseproject.utilities;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Shivang Goel  on 1/2/16.
 */
public class ToastUtils {

    /**
     * Private Instance Variable
     */
    private static ToastUtils classInstance = null;

    /**
     * Private Constructor to make this class singleton
     */
    private ToastUtils(){}

    /**
     * Method return the class instance
     * @return ToastUtils
     */
    public static ToastUtils getInstance(){
        if(classInstance == null){
            classInstance = new ToastUtils();
        }
        return classInstance;
    }

    /**
     * Shows a (long) toast
     */
    public void showToastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Shows a (long) toast.
     */
    public void showToastLong(Context context, int resourceId) {
        Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_LONG).show();
    }

    /**
     * Shows a (short) toast
     */
    public void showToastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a (short) toast
     */
    public void showToastShort(Context context, int resourceId) {
        Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_SHORT).show();
    }

    /**
     * Method call to clean object from memory
     */
    public void cleanObject(){
        classInstance = null;
    }
}
