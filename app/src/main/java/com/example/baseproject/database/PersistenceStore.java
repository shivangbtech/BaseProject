package com.example.baseproject.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PersistenceStore {

	/**
	 * Private Instance Variable
	 */
	private static PersistenceStore classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private PersistenceStore(){}

	/**
	 * Method return the class instance
	 * @return ToastUtils
	 */
	public static PersistenceStore getInstance(){
		if(classInstance == null){
			classInstance = new PersistenceStore();
		}
		return classInstance;
	}

	
	public void SavePreferences(Context context, String key, String value){
//		SharedPreferences sharedPreferences = _context.getPreferences(MODE_PRIVATE);  
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPreferences.edit();    
		editor.putString(key, value);   
		editor.commit(); 
		}    
	
	public String LoadPreferences(Context context, String key, String defaultValue){
//		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE); 
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		String response = sharedPreferences.getString(key, defaultValue); 
		return response;
		}
	
	public void SavePreferences(Context context, String key, boolean value){
//		SharedPreferences sharedPreferences = _context.getPreferences(MODE_PRIVATE);  
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPreferences.edit();    
		editor.putBoolean(key, value);   
		editor.commit(); 
		}    
	
	public boolean LoadPreferences(Context context, String key, boolean defaultValue){
//		SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE); 
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		boolean response = sharedPreferences.getBoolean(key, defaultValue); 
		return response;
		}
	
	public void clearPreferences(Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPreferences.edit();    
		editor.clear();   
		editor.commit();
	}

	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}
	
}
