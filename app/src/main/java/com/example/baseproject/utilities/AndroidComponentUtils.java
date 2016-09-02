package com.example.baseproject.utilities;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Shivang Goel  on 19/02/16.
 */
public class AndroidComponentUtils {

    /**
     * Private Instance Variable
     */
    private static AndroidComponentUtils classInstance = null;

    /**
     * Private Constructor to make this class singleton
     */
    private AndroidComponentUtils(){}

    /**
     * Method return the class instance
     * @return AndroidComponentUtils
     */
    public static AndroidComponentUtils getInstance(){
        if(classInstance == null){
            classInstance = new AndroidComponentUtils();
        }
        return classInstance;
    }


    public void toggleComponent(Context context, Class componentClass, boolean enable) {
        ComponentName componentName = new ComponentName(context, componentClass);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(componentName,
                enable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED :
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    public boolean isServiceRunning(Context context, Class serviceClass) {
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method tells that is any application is in foreground
     * @param context
     * @return
     */
    public boolean isAppForground(Context context) {
        ActivityManager mActivityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> l = mActivityManager.getRunningAppProcesses();
        Iterator<ActivityManager.RunningAppProcessInfo> i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = i.next();
            if (info.uid == context.getApplicationInfo().uid && info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentTopActivity(Context context) {

        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
        return ar.topActivity.getClassName().toString();
    }

    /**
     * Method call to clean object from memory
     */
    public void cleanObject(){
        classInstance = null;
    }
}
