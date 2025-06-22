package com.pnu.pnuguide.data

import android.content.Context

object StampProgress {
    private const val PREF_NAME = "stamps"
    private const val KEY_COUNT = "count"
    const val MAX = 24

    fun getProgress(context: Context): Int {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_COUNT, 0)
    }

    fun increment(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val current = prefs.getInt(KEY_COUNT, 0)
        prefs.edit().putInt(KEY_COUNT, (current + 1).coerceAtMost(MAX)).apply()
    }
}
