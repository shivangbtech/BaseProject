package com.example.baseproject.utilities;

import android.view.MotionEvent;
import android.view.View;

public class TouchUtils {

	public static boolean isTouchInsideView(final MotionEvent ev, final View currentFocus) {
		final int[] loc = new int[2];
		currentFocus.getLocationOnScreen(loc);
		return ev.getRawX() > loc[0] && ev.getRawY() > loc[1] && ev.getRawX() < (loc[0] + currentFocus.getWidth())
				&& ev.getRawY() < (loc[1] + currentFocus.getHeight());
	}
	
}
