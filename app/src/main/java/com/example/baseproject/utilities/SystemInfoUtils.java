package com.example.baseproject.utilities;

import java.util.UUID;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class SystemInfoUtils {

	public String getIMEI(Context ctx){
		TelephonyManager tmanager = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tmanager.getDeviceId();
		return imei;
	}
	
	public static String getUuid(Context context){
		final TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String deviceId = deviceUuid.toString();
		return deviceId;
	}

	public static String androidUniqueId(Context context){
		String android_id = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID); 
		return android_id;
	}
	
}
