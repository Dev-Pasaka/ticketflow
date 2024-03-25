package com.unbuniworks.camusat.efiber.data.local.sharedPreference

import android.app.Activity

interface SharedPreferenceRepository {
    suspend fun getString(key: String, activity: Activity):String?
    suspend fun setString(key: String,value:String?, activity: Activity):Boolean
}