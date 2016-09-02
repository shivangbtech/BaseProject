package com.example.baseproject.utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontUtils {
	
	 private static Typeface robotoTypeFace;
	    private static Typeface normal;
	    private static Typeface bold;
	    private static Typeface condensed;
	    private static Typeface light;
	
	public void setFont(Context context, String fontName, TextView view){
        
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(context.getAssets(), fontName);
        // Applying font
        view.setTypeface(tf);
	}
	
	public void setHeaderFont(Context context, TextView view){
		
		// Font path
        String fontPath = "fonts/cooper_std_black.ttf";
		// Loading Font Face
        Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);
        // Applying font
        view.setTypeface(tf);
	}
	
	
	  public static void setRobotoFont (Context context, View view)
	    {
	        if (robotoTypeFace == null)
	        {
	            robotoTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/horrendo.ttf");
	        }
	        setFont(view, robotoTypeFace);
	    }

	    private static void setFont (View view, Typeface robotoTypeFace)
	    {
	        if (view instanceof ViewGroup)
	        {
	            for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
	            {
	                setFont(((ViewGroup)view).getChildAt(i), robotoTypeFace);
	            }
	        }
	        else if (view instanceof TextView)
	        {
	            ((TextView) view).setTypeface(robotoTypeFace);
	        }
	    }
	    
	    
	    public static void setTypeFace(Typeface typeFace, ViewGroup parent){
	        for (int i = 0; i < parent.getChildCount(); i++) {
	            View v = parent.getChildAt(i);
	            if (v instanceof ViewGroup) {
	                setTypeFace(typeFace, (ViewGroup) v);
	            } else if (v instanceof TextView) {
	                TextView tv = (TextView) v;
	                tv.setTypeface(typeFace);
	                //For making the font anti-aliased.
	                tv.setPaintFlags(tv.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
	            }
	        }
	    }
	    
	 
	    private static void processsViewGroup(ViewGroup v, final int len) {

	      for (int i = 0; i < len; i++) {
	        final View c = v.getChildAt(i);
	        if (c instanceof TextView) {
	          setCustomFont((TextView) c);
	        } else if (c instanceof ViewGroup) {
	          setCustomFont((ViewGroup) c);
	        }
	      }
	    }

	    private static void setCustomFont(TextView c) {
	      Object tag = c.getTag();
	      if (tag instanceof String) {
	        if (((String) tag).contains("bold")) {
	          c.setTypeface(bold);
	          return;
	        }
	        if (((String) tag).contains("condensed")) {
	          c.setTypeface(condensed);
	          return;
	        }
	        if (((String) tag).contains("light")) {
	          c.setTypeface(light);
	          return;
	        }
	      }
	      c.setTypeface(normal);
	    }

	    public static void setCustomFont(View topView, AssetManager assetsManager) {
	      if (normal == null || bold == null || condensed == null || light == null) {
	        normal = Typeface.createFromAsset(assetsManager, "fonts/horrendo.ttf");
	        bold = Typeface.createFromAsset(assetsManager, "fonts/horrendo.ttf");
	        condensed = Typeface.createFromAsset(assetsManager, "fonts/horrendo.ttf");
	        light = Typeface.createFromAsset(assetsManager, "fonts/horrendo.ttf");
	      }

	      if (topView instanceof ViewGroup) {
	        setCustomFont((ViewGroup) topView);
	      } else if (topView instanceof TextView) {
	        setCustomFont((TextView) topView);
	      }
	    }

	    private static void setCustomFont(ViewGroup v) {
	      final int len = v.getChildCount();
	      processsViewGroup(v, len);
	    }

}
