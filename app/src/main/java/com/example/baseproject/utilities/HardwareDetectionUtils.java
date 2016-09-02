package com.example.baseproject.utilities;

/**
 *  @author Shivang Goel
 *  Start Date: 24/12/2013
 *  Modify Date:
 *  Modify By:
 *  Motive: Provide hardware detection
 */

import android.content.Context;
import android.content.pm.PackageManager;

public class HardwareDetectionUtils {

	/**
	 * Private Instance Variable
	 */
	private static HardwareDetectionUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private HardwareDetectionUtils(){}

	/**
	 * Method return the class instance
	 * @return HardwareDetectionUtils
	 */
	public static HardwareDetectionUtils getInstance(){
		if(classInstance == null){
			classInstance = new HardwareDetectionUtils();
		}
		return classInstance;
	}
	/**
	 * method call to check GPS hardware
	 * @param context
	 * @return boolean
	 */
	public boolean isGPSHardware(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)){
	        // this device has a GPS
	        return true;
	    } else {
	        // no camera on this GPS
	        return false;
	    }
	}
	
	/**
	 * Method call to Check if this device has a camera
	 * @param context
	 * @return boolean
	 */
	public boolean isCameraHardware(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
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