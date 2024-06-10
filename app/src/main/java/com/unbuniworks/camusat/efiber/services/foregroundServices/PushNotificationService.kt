package com.unbuniworks.camusat.efiber.services.foregroundServices

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.usecase.SendFCMToken
import com.unbuniworks.camusat.efiber.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PushNotificationService(
    private val sendFCMToken: SendFCMToken = SendFCMToken(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()

) : FirebaseMessagingService() {
    private val CHANNEL_ID = "EfiberDispath"
    private val CHANNEL_NAME = "Efiber"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Handle the new token
        CoroutineScope(Dispatchers.IO).launch {
            val sharedPref = this@PushNotificationService
                .applicationContext
                .getSharedPreferences(
                    "com.unbuniworks.camusat.efiber.PREFERENCE_FILE_KEY",
                    Context.MODE_PRIVATE
                )
            with(sharedPref.edit()) {
                putString(Constants.fcmToken, token)
                apply()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
        Log.d("FCMToken", "Refreshed token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        // Get the notification title and body
        val title = message.notification?.title ?: "No Title"
        val body = message.notification?.body ?: "No Body"

        // Create an intent that opens MainActivity when the notification is clicked
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("notification_screen", "notification")
            putExtra("notification_title", title)
            putExtra("notification_body", body)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Display a notification to the user
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.camusat_logo_icon) // Ensure this resource exists
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(0, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}