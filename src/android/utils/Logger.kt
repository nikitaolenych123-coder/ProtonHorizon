package com.protonhorizon.emulator.utils

import android.util.Log

/**
 * Логер для всього проекту
 */
object Logger {
    private const val TAG = "ProtonHorizon"
    private var isDebugEnabled = true
    
    fun setDebugEnabled(enabled: Boolean) {
        isDebugEnabled = enabled
    }
    
    fun d(message: String, throwable: Throwable? = null) {
        if (isDebugEnabled) {
            Log.d(TAG, message, throwable)
        }
    }
    
    fun i(message: String, throwable: Throwable? = null) {
        Log.i(TAG, message, throwable)
    }
    
    fun w(message: String, throwable: Throwable? = null) {
        Log.w(TAG, message, throwable)
    }
    
    fun e(message: String, throwable: Throwable? = null) {
        Log.e(TAG, message, throwable)
    }
    
    fun tag(customTag: String): TaggedLogger = TaggedLogger(customTag)
    
    class TaggedLogger(private val tag: String) {
        fun d(message: String, throwable: Throwable? = null) {
            if (isDebugEnabled) {
                Log.d("$TAG-$tag", message, throwable)
            }
        }
        
        fun i(message: String, throwable: Throwable? = null) {
            Log.i("$TAG-$tag", message, throwable)
        }
        
        fun w(message: String, throwable: Throwable? = null) {
            Log.w("$TAG-$tag", message, throwable)
        }
        
        fun e(message: String, throwable: Throwable? = null) {
            Log.e("$TAG-$tag", message, throwable)
        }
    }
}
