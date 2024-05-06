package com.unbuniworks.camusat.efiber.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.ServiceCompat.startForeground
import com.jakewharton.threetenabp.AndroidThreeTen

class EFiber:Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channel = NotificationChannel(
                "work_order",
                "New work orders",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}