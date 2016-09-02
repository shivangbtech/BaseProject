package com.example.baseproject.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

public class DisplayUtils {
	
	/**
	 * Method call to get Display Width and Height
	 * @param context
	 * @return int Array with Width and Height
	 */
	public int[] getDisplayMetricsWidthHeight(Context context){
		DisplayMetrics metrics = new DisplayMetrics();        
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;     
		int width = metrics.widthPixels;
		int [] result = {width, height};
		return result;
	}

	public int[] getDisplayWidthHeight(Context context){
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		int [] result = {width, height};
		return result;
	}

	public static int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
		float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
		return Math.round(pixels);
	}

	public static float dpToPx(Context context, float valueInDp) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
	}

	/**
	 * This method converts device specific pixels to density independent pixels.
	 *
	 * @param px A value in px (pixels) unit. Which we need to convert into db
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context){
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
		return dp;
	}

	public String getDensity(Activity activity) {
		String r;
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		if (metrics.densityDpi <= DisplayMetrics.DENSITY_LOW) {
			r = "ldpi";
		} else if (metrics.densityDpi <= DisplayMetrics.DENSITY_MEDIUM) {
			r = "mdpi";
		} else  if (metrics.densityDpi <= DisplayMetrics.DENSITY_HIGH){
			r = "hdpi";
		}else  if (metrics.densityDpi <= DisplayMetrics.DENSITY_XHIGH){
			r = "xhdpi";
		}else  if (metrics.densityDpi <= DisplayMetrics.DENSITY_XXHIGH){
			r = "xxhdpi";
		}else {
			r = "xxxhdpi";
		}
		return r;
	}
}
