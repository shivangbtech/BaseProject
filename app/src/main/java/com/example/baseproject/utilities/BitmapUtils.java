package com.example.baseproject.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

/**
 * Created by Shivang Goel  on 19/02/16.
 */
public class BitmapUtils {
	/**
	 * Private Instance Variable
	 */
	private static BitmapUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private BitmapUtils(){}

	/**
	 * Method return the class instance
	 * @return BitmapUtils
	 */
	public static BitmapUtils getInstance(){
		if(classInstance == null){
			classInstance = new BitmapUtils();
		}
		return classInstance;
	}

	/**
	 * Method call to give Bitmap in circular shape
	 * @param bmp
	 * @param radius
	 * @return Bitmap
	 */
	public Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
		Bitmap sbmp;
		if (bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;
		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

//		final int color = 0xffa19774;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f,
				sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(sbmp, rect, rect, paint);

		return output;
	}
	
	/**
	 * Methos call to write Text on a bitmap
	 * @param context
	 * @param drawableId
	 * @param text
	 * @return Bitmap
	 */
	public Bitmap writeTextOnDrawable(Context context, int drawableId, String text) {

		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), drawableId).copy(Config.ARGB_8888, true);

		Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

		Paint paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTypeface(tf);
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(convertToPixels(context, 11));

		Rect textRect = new Rect();
		paint.getTextBounds(text, 0, text.length(), textRect);

		Canvas canvas = new Canvas(bm);

		//If the text is bigger than the canvas , reduce the font size
		if(textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
			paint.setTextSize(convertToPixels(context, 7));        //Scaling needs to be used for different dpi's

		//Calculate the positions
		int xPos = (canvas.getWidth() / 2) - 2;     //-2 is for regulating the x position offset

		//"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
		int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;  

		canvas.drawText(text, xPos, yPos, paint);

		return  bm;
	}

	/**
	 * Method call to convert dp to pixels
	 * @param context
	 * @param nDP
	 * @return integer
	 */
	public int convertToPixels(Context context, int nDP){
		final float conversionScale = context.getResources().getDisplayMetrics().density;
		return (int) ((nDP * conversionScale) + 0.5f) ;
	}


	/**
	 * Method call to Convert a view to bitmap
	 * @param context
	 * @param view
	 * @return Bitmap
	 */
	public Bitmap createDrawableFromView(Context context, View view) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.buildDrawingCache();
		Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);
		view.draw(canvas);

		return bitmap;
	}

	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}

}
