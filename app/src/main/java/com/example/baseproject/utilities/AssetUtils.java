package com.example.baseproject.utilities;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetUtils {

	/**
	 * Private Instance Variable
	 */
	private static AssetUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private AssetUtils(){}

	/**
	 * Method return the class instance
	 * @return AssetUtils
	 */
	public static AssetUtils getInstance(){
		if(classInstance == null){
			classInstance = new AssetUtils();
		}
		return classInstance;
	}

	/**
	 * Read plist from Assets
	 * @param context
	 * @return String
	 */
		public String readPlistFromAssets(Context context, String fileName) {
			StringBuffer sb = new StringBuffer();
			BufferedReader br=null;
			try {
				br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName + ".plist")));
				String temp;
				while ((temp = br.readLine()) != null)
					sb.append(temp);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close(); // stop reading
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			Log.i(getClass().getSimpleName(), sb.toString());
			return sb.toString();
		}

	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}
	
}
