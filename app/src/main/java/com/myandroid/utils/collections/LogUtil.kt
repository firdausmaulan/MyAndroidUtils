package com.myandroid.utils.collections

import android.util.Log

import com.myandroid.utils.BuildConfig

object LogUtil {
    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

    fun d(message: String) {
        if (BuildConfig.DEBUG) Log.d("log_debug", message)
    }

    fun i(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.i(tag, message)
    }

    fun i(message: String) {
        if (BuildConfig.DEBUG) Log.i("log_info", message)
    }

    fun e(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message)
    }

    fun e(message: String) {
        if (BuildConfig.DEBUG) Log.e("log_error", message)
    }
}
