package com.unbuniworks.camusat.efiber.data.local.sharedPreference

import android.app.Activity
import android.app.Application
import android.content.Context

class SharedPreferenceRepositoryImpl: SharedPreferenceRepository {
    override suspend fun getString(key: String, activity: Activity): String? {

        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return ""
        return sharedPref.getString(key, "")

    }

    override suspend fun setString(key: String, value:String?, activity: Activity): Boolean {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return false
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
        return true
    }
}