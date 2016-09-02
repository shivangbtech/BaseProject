package com.example.baseproject.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * @author Shivang
 */

public class ImageResizeUtils {

	/**
	 * Mehod call to resize Bitmap
	 * @param bm
	 * @param newWidth
	 * @return bitmap
	 */
	public Bitmap getResizedBitmap(Bitmap bm, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float aspect = (float)width / height;
		float scaleWidth = newWidth;
		float scaleHeight = scaleWidth / aspect;      
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth / width, scaleHeight / height);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		bm.recycle();
		return resizedBitmap;
	}

	/**
	 * Mehod call to resize Bitmap
	 * @param bm
	 * @param newWidth
	 * @param newHeight
	 * @return bitmap
	 */
	public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = newWidth;
		float scaleHeight = newHeight;      
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth / width, scaleHeight / height);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
		bm.recycle();
		return resizedBitmap;
	}

	/**
	 * Method call to resize image
	 * @param input
	 * @param destWidth
	 * @param destHeight
	 * @return bitmap
	 */
	public Bitmap resizeBitmap( Bitmap input, int destWidth, int destHeight ){
		int srcWidth = input.getWidth();
		int srcHeight = input.getHeight();
		boolean needsResize = false;
		float p;
		if ( srcWidth > destWidth || srcHeight > destHeight ) {
			needsResize = true;
			if ( srcWidth > srcHeight && srcWidth > destWidth ) {
				p = (float)destWidth / (float)srcWidth;
				destHeight = (int)( srcHeight * p );
			} else {
				p = (float)destHeight / (float)srcHeight;
				destWidth = (int)( srcWidth * p );
			}
		} else {
			destWidth = srcWidth;
			destHeight = srcHeight;
		}
		if ( needsResize ) {
			Bitmap output = Bitmap.createScaledBitmap( input, destWidth, destHeight, true );
			return output;
		} else {
			return input;
		}
	}

	/**
	 * Method call to resize bitmap
	 * @param bitmap
	 * @param i
	 * @param j
	 * @return bitmap
	 */
	public Bitmap resize(Bitmap bitmap, int i, int j) {
		Bitmap bitmap1 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap1);
		Paint paint = new Paint(2);
		canvas.drawBitmap(bitmap, null, new Rect(0, 0, i, j), paint);
		return bitmap1;
	}

	/**
	 * Method call to get resized bitmap from file
	 * @param path
	 * @param reqWidth
	 * @param reqHeight
	 * @return bitmap
	 */
	public Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) { 
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// Calculate inSampleSize
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		int inSampleSize = 1;
		if (height > reqHeight) {
			inSampleSize = Math.round((float)height / (float)reqHeight);
		}
		int expectedWidth = width / inSampleSize;
		if (expectedWidth > reqWidth) {
			//if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
			inSampleSize = Math.round((float)width / (float)reqWidth);
		}
		options.inSampleSize = inSampleSize;
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}

	public int calculateInSampleSize(int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float)height / (float)reqHeight);
			} else {
				inSampleSize = Math.round((float)width / (float)reqWidth);
			}	
		}
		return inSampleSize;
	}
}

/**
 * @author Shivang
 */