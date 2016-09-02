package com.example.baseproject.utilities;

/**
 * @author Shivang Goel
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Base64Utils {

	/**
	 * Private Instance Variable
	 */
	private static Base64Utils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private Base64Utils(){}

	/**
	 * Method return the class instance
	 * @return Base64Utils
	 */
	public static Base64Utils getInstance(){
		if(classInstance == null){
			classInstance = new Base64Utils();
		}
		return classInstance;
	}


	/**
	 * Method to convert String to Base64 String
	 * @param string
	 * @return Base64 String
	 */
	public String encodeToBase64(String string) throws Exception {
		String encodedString = "";
		try {
			byte[] byteData = null;
			if (Build.VERSION.SDK_INT >= 8) {
				byteData = Base64.encode(string.getBytes(), Base64.DEFAULT);
			}
			encodedString = new String(byteData);
		} catch (Exception e) {
			Logger.getInstance().log("Error to convert Base64 String", e);
		}

		return encodedString;
	}

	/**
	 * Method call to convert Bitmap to base 64 String
	 * @param image
	 * @return converted string
	 */
	public String encodeTobase64(Bitmap image){
		Bitmap immagex=image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
		return imageEncoded;
	}

	/**
	 * Method call to decode base 64 string into bitmap
	 * @param input: base 64 string
	 * @return bitmap
	 */
	public Bitmap decodeBase64(String input){
		byte[] decodedByte = Base64.decode(input, 0);
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
	}
	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}
}

/**
 * @author Shivang Goel
 */
