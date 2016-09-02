package com.example.baseproject.utilities;

import android.view.View;
import android.view.ViewGroup;

public class LayoutUtils {

	public static void setLayoutHeight(View view, int height){
		ViewGroup.LayoutParams params = view.getLayoutParams();
		params.height = height;
		view.setLayoutParams(params);
		view.requestLayout();
	}

	public static void setLayoutWidth(View view, int width){
		ViewGroup.LayoutParams params = view.getLayoutParams();
		params.width = width;
		view.setLayoutParams(params);
		view.requestLayout();
	}

	
	
}
