package com.technorapper.myfriendsbday.data

import android.content.Context
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(@ApplicationContext context: Context) {
    var LAST_SYNC = "lastSync"
    val prefs = context.getSharedPreferences(
        "my_preferences", Context.MODE_PRIVATE
    );

    fun getSync(): Long {
        return prefs.getLong(LAST_SYNC, 0)!!
    }

    fun setSync(query: Long) {
        prefs.edit().putLong(LAST_SYNC, query).apply()
    }
}