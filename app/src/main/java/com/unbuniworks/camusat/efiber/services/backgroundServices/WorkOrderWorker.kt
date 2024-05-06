package com.unbuniworks.camusat.efiber.services.backgroundServices

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.unbuniworks.camusat.efiber.services.foregroundServices.NotificationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class WorkOrderWorker(
    val context:Context,
    workerParameters: WorkerParameters
):Worker(context, workerParameters) {
    override  fun doWork(): Result {
       /* Intent(context, NotificationService::class.java).also {
            it.action = NotificationService.Actions.START.toString()
            context.startService(it)

        }*/
        return Result.success()
    }


}