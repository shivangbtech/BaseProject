package com.example.baseproject.utilities;

/**
 * @author Shivang Goel
 */

import android.util.Log;

import com.example.baseproject.variables.GlobalVariables;


public class Logger {

    public static Logger logger = null;

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    /**
     * Method to show log
     *
     * @param message
     */
    public void log(String message) {
        if (GlobalVariables.showLog) {
            Log.v(GlobalVariables.logTAG, message);
        }
    }

    /**
     * Method to show log
     *
     * @param message
     * @param ex
     */
    public void log(String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.v(GlobalVariables.logTAG, message, ex);
        }
    }

    /**
     * Method to show log
     *
     * @param tag
     * @param message
     */
    public void log(String tag, String message) {
        if (GlobalVariables.showLog) {
            Log.v(getAppTag(tag), message);
        }
    }

    /**
     * Method to show log
     *
     * @param tag
     * @param message
     * @param ex
     */
    public void log(String tag, String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.v(getAppTag(tag), message, ex);
        }
    }

    /**
     * Method to show log info
     *
     * @param message
     */
    public void log_info(String message) {
        if (GlobalVariables.showLog) {
            Log.i(GlobalVariables.logTAG, message);
        }
    }

    /**
     * Method to show log info
     *
     * @param message
     * @param ex
     */
    public void log_info(String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.i(GlobalVariables.logTAG, message, ex);
        }
    }

    /**
     * Method to show log info
     *
     * @param tag
     * @param message
     */
    public void log_info(String tag, String message) {
        if (GlobalVariables.showLog) {
            Log.i(getAppTag(tag), message);
        }
    }

    /**
     * Method to show log_info
     *
     * @param tag
     * @param message
     * @param ex
     */
    public void log_info(String tag, String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.i(getAppTag(tag), message, ex);
        }
    }

    /**
     * Method to show log error
     *
     * @param message
     */
    public void log_error(String message) {
        if (GlobalVariables.showLog) {
            Log.e(GlobalVariables.logTAG, message);
        }
    }

    /**
     * Method to show log error
     *
     * @param message
     * @param ex
     */
    public void log_error(String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.e(GlobalVariables.logTAG, message, ex);
        }
    }

    /**
     * Method to show log error
     *
     * @param tag
     * @param message
     */
    public void log_error(String tag, String message) {
        if (GlobalVariables.showLog) {
            Log.e(getAppTag(tag), message);
        }
    }

    /**
     * Method to show log error
     *
     * @param tag
     * @param message
     * @param ex
     */
    public void log_error(String tag, String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.e(getAppTag(tag), message, ex);
        }
    }

    private String getAppTag(String tag) {
        if (tag != null && tag.trim().length() > 0) {
            return GlobalVariables.logTAG + " : " + tag;
        } else {
            return GlobalVariables.logTAG;
        }
    }


    /**
     * Method to show log debug
     *
     * @param message
     */
    public void log_debug(String message) {
        if (GlobalVariables.showLog) {
            Log.d(GlobalVariables.logTAG, message);
        }
    }

    /**
     * Method to show log debug
     *
     * @param message
     * @param ex
     */
    public void log_debug(String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.d(GlobalVariables.logTAG, message, ex);
        }
    }

    /**
     * Method to show log debug
     *
     * @param tag
     * @param message
     */
    public void log_debug(String tag, String message) {
        if (GlobalVariables.showLog) {
            Log.d(getAppTag(tag), message);
        }
    }

    /**
     * Method to show log debug
     *
     * @param tag
     * @param message
     * @param ex
     */
    public void log_debug(String tag, String message, Throwable ex) {
        if (GlobalVariables.showLog) {
            Log.d(getAppTag(tag), message, ex);
        }
    }

    /**
     * Method call to clean object from memory
     */
    public void cleanObject() {
        logger = null;
    }
}

/**
 * @author Shivang Goel
 */