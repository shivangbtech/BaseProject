/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.baseproject.utilities;

import android.util.Log;

public class LoggerClass {
    private static final String APP_TAG = "AndroidMobileLib";

    /**
     * Use this method if you want your class name to be prefixed to each log output.
     */
    public static LoggerClass getLogger(Class<?> classZ) {
        return new LoggerClass(classZ.getSimpleName() + ": ");
    }

    /**
     * Use this factory method if you DO NOT want your class name to be prefixed into the log
     * output.
     */
    public static LoggerClass getLogger() {
        return new LoggerClass();
    }

    private final String mLogPrefix;

    /** No custom log prefix used. */
    private LoggerClass() {
        mLogPrefix = null;
    }

    /** Use the supplied custom prefix in log output. */
    private LoggerClass(String logPrefix) {
        mLogPrefix = logPrefix;
    }

    private String getMsg(String msg) {
        if (mLogPrefix != null) {
            return mLogPrefix + msg;
        } else {
            return msg;
        }
    }

    public void i(String msg) {
        Log.i(APP_TAG, getMsg(msg));
    }

    public void i(String msg, Throwable t) {
        Log.i(APP_TAG, getMsg(msg), t);
    }

    public void d(String msg) {
        Log.d(APP_TAG, getMsg(msg));
    }

    public void d(String msg, Throwable t) {
        Log.d(APP_TAG, getMsg(msg), t);
    }

    public void w(String msg) {
        Log.w(APP_TAG, getMsg(msg));
    }

    public void w(String msg, Throwable t) {
        Log.w(APP_TAG, getMsg(msg), t);
    }

    public void e(String msg) {
        Log.e(APP_TAG, getMsg(msg));
    }

    public void e(String msg, Throwable t) {
        Log.e(APP_TAG, getMsg(msg), t);
    }
}
