package com.example.baseproject.utilities;

/**
 * @author Shivang Goel
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baseproject.R;


public class AlertUtils {

	/**
	 * Private Instance Variable
	 */
	private static AlertUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private AlertUtils(){}

	/**
	 * Method return the class instance
	 * @return AlertUtils
	 */
	public static AlertUtils getInstance(){
		if(classInstance == null){
			classInstance = new AlertUtils();
		}
		return classInstance;
	}

	/**
	 * Method to show custom Dialog
	 * @param context
	 * @param title
	 * @param message
	 */

//	@SuppressWarnings("deprecation")
	public void pop_Alert_Custom(Context context, String title, String message) {
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_alert_layout);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(false);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);

		// Heading Text View
		TextView headingText = (TextView)dialog.findViewById(R.id.headingText);
		headingText.setText(title);

		// Description Text View
		TextView descText = (TextView)dialog.findViewById(R.id.descText);
		descText.setText(message);	

		// Close Button                  
		ImageButton close_btn= (ImageButton) dialog.findViewById(R.id.close_btn);
		close_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	/**
	 * Method to show Custom Dialog
	 * @param context
	 * @param title
	 * @param message
	 * @param isHeaderBar
	 */

//	@SuppressWarnings("deprecation")
	public void pop_Alert_Custom(Context context, String title, String message, boolean isHeaderBar){
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_alert_layout); 
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(false);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);

		// Heading Text View
		TextView headingText = (TextView)dialog.findViewById(R.id.headingText);
		headingText.setText(title);

		// Description Text View
		TextView descText = (TextView)dialog.findViewById(R.id.descText);
		descText.setText(message);	

		LinearLayout headerBar = (LinearLayout)dialog.findViewById(R.id.headerBar);
		if(isHeaderBar){
			headerBar.setVisibility(View.VISIBLE);
		}else{
			headerBar.setVisibility(View.GONE);
		}
		if(title==null || title.equalsIgnoreCase("")){
			headingText.setVisibility(View.GONE);
		}

		// Close Button                  
		ImageButton close_btn= (ImageButton) dialog.findViewById(R.id.close_btn);
		close_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	
	/**
	 * Method to show Custom Dialog with exit when close
	 * @param context
	 * @param title
	 * @param message
	 * @param isHeaderBar
	 * @param isExist
	 */
//	@SuppressWarnings("deprecation")
	public void pop_Alert_Custom(final Context context, String title, String message, boolean isHeaderBar, final boolean isExist){
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_alert_layout); 
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(false);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);

		// Heading Text View
		TextView headingText = (TextView)dialog.findViewById(R.id.headingText);
		headingText.setText(title);

		// Description Text View
		TextView descText = (TextView)dialog.findViewById(R.id.descText);
		descText.setText(message);	

		LinearLayout headerBar = (LinearLayout)dialog.findViewById(R.id.headerBar);
		if(isHeaderBar){
			headerBar.setVisibility(View.VISIBLE);
		}else{
			headerBar.setVisibility(View.GONE);
		}
		if(title==null || title.equalsIgnoreCase("")){
			headingText.setVisibility(View.GONE);
		}

		// Close Button                  
		ImageButton close_btn= (ImageButton) dialog.findViewById(R.id.close_btn);
		close_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isExist){
//					((Activity) context).finish();
					
					System.exit(0);
				}
				dialog.dismiss();
			}
		});
	}

	/**
	 * Method call to show dialog that will close activity
	 * @param context
	 * @param title
	 * @param message
	 * @param isHeaderBar
	 */
	public void pop_Alert_Custom_Activity_close(final Context context, String title, String message, boolean isHeaderBar){
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_alert_layout); 
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(false);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);

		// Heading Text View
		TextView headingText = (TextView)dialog.findViewById(R.id.headingText);
		headingText.setText(title);

		// Description Text View
		TextView descText = (TextView)dialog.findViewById(R.id.descText);
		descText.setText(message);	

		LinearLayout headerBar = (LinearLayout)dialog.findViewById(R.id.headerBar);
		if(isHeaderBar){
			headerBar.setVisibility(View.VISIBLE);
		}else{
			headerBar.setVisibility(View.GONE);
		}
		if(title==null || title.equalsIgnoreCase("")){
			headingText.setVisibility(View.GONE);
		}

		// Close Button                  
		ImageButton close_btn= (ImageButton) dialog.findViewById(R.id.close_btn);
		close_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
					((Activity) context).finish();
				
				dialog.dismiss();
			}
		});
	}


	
	/**
	 * Method to show AlertUtils Dialog with Positive Button Listener
	 * @param context
	 * @param title
	 * @param message
	 * @param positive_button_dialog_ClickListener
	 */

	public void pop_Alert(Context context, String title, String message, DialogInterface.OnClickListener positive_button_dialog_ClickListener){
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Yes",/* new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
				dialog.cancel();

			}
		}*/positive_button_dialog_ClickListener);
		alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		//		alertDialog.setIcon(R.drawable.validation_icon);
		alertDialog.show();
	}
	
	/**
	 * Method to show AlertUtils Dialog for Network Connection
	 * @param context
	 */

//	@SuppressWarnings("deprecation")
	public void pop_Alert_Network(Context context) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(context.getResources().getString(R.string.alert_network_title));
		alertDialog.setMessage(context.getResources().getString(R.string.alert_network_desc));
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//				System.exit(0);
				dialog.cancel();
			}
		});
		//		alertDialog.setIcon(R.drawable.validation_icon);
		alertDialog.show();
	}

	/**
	 * Method to show Network AlertUtils Dialog that further exit from application
	 * @param context
	 */

//	@SuppressWarnings("deprecation")
	public void pop_Alert_Network_Exit(Context context){
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(context.getResources().getString(R.string.alert_network_title));
		alertDialog.setMessage(context.getResources().getString(R.string.alert_network_desc));
		alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
				dialog.cancel();
			}
		});
		//		alertDialog.setIcon(R.drawable.validation_icon);
		alertDialog.show();
	}

	/**
	 * Method show Custom Network Dialog
	 * @param context
	 */

//	@SuppressWarnings("deprecation")
	public void pop_Alert_Network_Custom(Context context) {
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_alert_layout); 
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(false);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);

		// Heading Text View
		TextView headingText = (TextView)dialog.findViewById(R.id.headingText);
		headingText.setText(context.getResources().getString(R.string.alert_network_title));

		// Description Text View
		TextView descText = (TextView)dialog.findViewById(R.id.descText);
		descText.setText(context.getResources().getString(R.string.alert_network_desc));

		// Close Button                  
		ImageButton close_btn= (ImageButton) dialog.findViewById(R.id.close_btn);
		close_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	
	/**
	 * Method show Custom Network Dialog
	 * @param context
	 * @param isExit
	 */

//	@SuppressWarnings("deprecation")
	public void pop_Alert_Network_Custom(final Context context, final boolean isExit) {
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_alert_layout); 
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(false);
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);

		// Heading Text View
		TextView headingText = (TextView)dialog.findViewById(R.id.headingText);
		headingText.setText(context.getResources().getString(R.string.alert_network_title));

		// Description Text View
		TextView descText = (TextView)dialog.findViewById(R.id.descText);
		descText.setText(context.getResources().getString(R.string.alert_network_desc));

		// Close Button                  
		ImageButton close_btn= (ImageButton) dialog.findViewById(R.id.close_btn);
		close_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				if(isExit){
					((Activity)context).finish();
				}
			}
		});
	}

	/**
	 * Method to show AlertUtils Dialog that further exit from application
	 * @param context
	 * @param titleString
	 * @param messageString
	 */

//	@SuppressWarnings("deprecation")
	public void pop_Alert_Exit(Context context, String titleString, String messageString){
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(titleString);
		alertDialog.setMessage(messageString);
		alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
				dialog.cancel();
			}
		});
		//		alertDialog.setIcon(R.drawable.validation_icon);
		alertDialog.show();
	}
	
	/**
	 * Method to show default AlertUtils dialog
	 * @param context
	 * @param titleString
	 * @param messageString
	 */
//	@SuppressWarnings("deprecation")
	public void pop_Alert(Context context, String titleString, String messageString){
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(titleString);
		alertDialog.setMessage(messageString);
		alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
//				System.exit(0);
				dialog.cancel();
			}
		});
		//		alertDialog.setIcon(R.drawable.validation_icon);
		alertDialog.show();
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