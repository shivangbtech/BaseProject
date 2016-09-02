package com.example.baseproject.utilities;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Shivang Goel on 29/2/16.
 */
public class WhatsappUtils {

    /**
     * Private Instance Variable
     */
    private static WhatsappUtils classInstance = null;

    /**
     * Private Constructor to make this class singleton
     */
    private WhatsappUtils(){}

    /**
     * Method return the class instance
     * @return ToastUtils
     */
    public static WhatsappUtils getInstance(){
        if(classInstance == null){
            classInstance = new WhatsappUtils();
        }
        return classInstance;
    }

    public void sendTestMessage(Activity activity, String msgToShare) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msgToShare);
        sendIntent.setType("text/plain");
//        shareIntent.setType("image/jpeg");
        // Do not forget to add this to open whatsApp App specifically
        sendIntent.setPackage("com.whatsapp");
        activity.startActivity(sendIntent);

    }

    /**
     * Method call to clean object from memory
     */
    public void cleanObject(){
        classInstance = null;
    }
}
