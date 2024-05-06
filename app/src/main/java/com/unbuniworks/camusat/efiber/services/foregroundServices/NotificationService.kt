package com.unbuniworks.camusat.efiber.services.foregroundServices

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.unbuniworks.camusat.efiber.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class NotificationService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun start() {

        CoroutineScope(Dispatchers.Default).launch {
            newWorkOder().collect{wordOrderId ->
                val notification = NotificationCompat.Builder(this@NotificationService, "work_order")
                    .setSmallIcon(R.drawable.camusat_logo_icon)
                    .setContentTitle("New work order created")
                    .setContentText("WorkOrder id: $wordOrderId")
                    .build()
                startForeground(1, notification)
            }
        }

    }


    enum class Actions {
        START,
        STOP
    }


    private fun  newWorkOder(): Flow<String> = flow{
        while (true){
            emit((10000000..1000000000000).random().toString())
            delay(5000)
        }

    }

}