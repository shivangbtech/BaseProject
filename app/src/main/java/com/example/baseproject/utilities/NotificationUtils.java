package com.example.baseproject.utilities;

import android.app.NotificationManager;
import android.content.Context;

public class NotificationUtils {

	public static void clearNotification(Context context, int NOTIFICATION_ID){
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(NOTIFICATION_ID);
	}
	
	public static void clearAllNotification(Context context){
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancelAll();
	}

	
}
