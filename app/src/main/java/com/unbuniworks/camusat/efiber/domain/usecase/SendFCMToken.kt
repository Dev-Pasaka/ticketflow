package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.content.Context
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.repository.NotificationRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.repository.NotificationRepository

class SendFCMToken(
    private val notificationRepository: NotificationRepository = NotificationRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun sendFCMToken(token:Map<String, String>, authToken:String):Boolean {
        // Send the FCM token to the server

        return try {
            val response = notificationRepository.sendFCMToken(token = token, authToken = authToken)
            Log.e("FCMTokenResponse", response.toString())
            response
        }catch (e:Exception){
            false
        }
    }
}