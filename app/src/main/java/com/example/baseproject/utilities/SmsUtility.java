package com.example.baseproject.utilities;

/**
 * @author Shivang Goel
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;

public class SmsUtility {

	private final String TAG = getClass().getSimpleName();

	/**
	 * Method to send SMS to specific Mobile number using SmsManager
	 * @param phoneNumber
	 * @param message
	 * @return boolean
	 */

	public boolean sendSMSMessage(String phoneNumber, String message) {
		Log.i(TAG, "SMS Sending");
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNumber, null, message, null, null);
			Log.i(TAG, "SMS Sent");
			return true;
		} catch (Exception e) {
			Log.e(TAG, "SMS sending failed, please try again", e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Method call to send SMS using Intent
	 * @param context
	 * @param mobileNumber
	 * @param body
	 */
	public boolean sendSMS(Context context, String mobileNumber, String body) {
		Log.i(TAG, "SMS Sending");
		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setData(Uri.parse("smsto:"));
		smsIntent.setType("vnd.android-dir/mms-sms");
		smsIntent.putExtra("address"  , mobileNumber);
		smsIntent.putExtra("sms_body"  , body);
		try {
			context.startActivity(smsIntent);
			Log.i(TAG, "SMS Sent");
			return true;
		} catch (android.content.ActivityNotFoundException ex) {
			Log.i(TAG, "SMS sending failed, please try again", ex);
		}
		return false;
	}
	
	/**
	 * Method call to send sms using intent
	 * @param context
	 * @param body
	 * @return boolean
	 */
	public boolean sendSMS(Context context, String body) {
		Log.i(TAG, "SMS Sending");
		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setData(Uri.parse("smsto:"));
		smsIntent.setType("vnd.android-dir/mms-sms");
//		smsIntent.putExtra("address"  , mobileNumber);
		smsIntent.putExtra("sms_body"  , body);
		try {
			context.startActivity(smsIntent);
			Log.i(TAG, "SMS Sent");
			return true;
		} catch (android.content.ActivityNotFoundException ex) {
			Log.i(TAG, "SMS sending failed, please try again", ex);
		}
		return false;
	}
}

/**
 * @author Shivang Goel
 */