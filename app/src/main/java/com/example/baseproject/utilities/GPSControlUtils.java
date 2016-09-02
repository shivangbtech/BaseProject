package com.example.baseproject.utilities;

/**
 *  @author Shivang Goel
 *  Start Date: 24/12/2013
 *  Modify Date:
 *  Modify By:
 *  Motive: To provide various GPS controls and utilities
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

public class GPSControlUtils {

	/**
	 * Private Instance Variable
	 */
	private static GPSControlUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private GPSControlUtils(){}

	/**
	 * Method return the class instance
	 * @return ToastUtils
	 */
	public static GPSControlUtils getInstance(){
		if(classInstance == null){
			classInstance = new GPSControlUtils();
		}
		return classInstance;
	}

	/**
	 * Method call to automatically turn on GPS
	 */
	public void turnGPSOn(Context context){
		Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", true);
		context.sendBroadcast(intent);

		/* String provider = Settings.Secure.getString(_context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        _context.sendBroadcast(poke);
	    }*/
	}

	/**
	 * Method call to turned GPS OFF automatically
	 */
	public void turnGPSOff(Context context){
		Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", false);
		context.sendBroadcast(intent);

		/*	    String provider = Settings.Secure.getString(_context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
	    if(provider.contains("gps")){ //if gps is enabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        _context.sendBroadcast(poke);
	    }*/
	}


	/**
	 * Method call to open GPS Enabling setting page
	 * Get Location Manager and check for GPS & Network location services
	 * @return boolean
	 */
	public boolean enableGPSViaSetting(final Context context){
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			// Build the alert dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("Location Services Not Active");
			builder.setMessage("Please enable Location Services and GPS");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialogInterface, int i) {
					// Show location settings when the user acknowledges the alert dialog
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					context.startActivity(intent);
				}
			});
			Dialog alertDialog = builder.create();
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();
		}
		if(isGPSEnabled(context)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Method call to check GPS provider status
	 * @return boolean
	 */
	public boolean isGPSEnabled(Context context){
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Method call to check network provider status
	 * @return boolean
	 */
	public boolean isNetworkEnabled(Context context){
		LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}
}
/**
 * @Author Shivang Goel
 */