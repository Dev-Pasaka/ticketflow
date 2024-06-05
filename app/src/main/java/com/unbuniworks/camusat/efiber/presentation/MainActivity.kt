package com.unbuniworks.camusat.efiber.presentation

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.res.colorResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.NavGraph
import com.unbuniworks.camusat.efiber.presentation.ui.theme.CamusatTheme
import com.unbuniworks.camusat.efiber.services.backgroundServices.WorkOrderWorker
import java.time.Duration

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private var backClickCount = 0

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {

        if (!hasRequiredPermissions()){
            ActivityCompat.requestPermissions(
                this,
                permissions(),
                0
            )

        }

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }*/
        val workRequest = OneTimeWorkRequestBuilder<WorkOrderWorker>()
            .setInitialDelay(Duration.ofSeconds(10))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(1)
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)

        super.onCreate(savedInstanceState)
        setContent {
            CamusatTheme {
                // A surface container using the 'background' color from the theme

                // Remember a SystemUiController
                val systemUiController = rememberSystemUiController()
                val background = colorResource(id = R.color.background)
                SideEffect {
                    systemUiController.setSystemBarsColor(color = background)
                    systemUiController.setStatusBarColor(color = background)
                }
                navController = rememberNavController()
                NavGraph(navController = navController)

            }

        }

    }

    private fun handleNotificationIntent(intent: Intent, navController: NavController) {
        val notificationScreen = intent.getStringExtra("notification_screen")
        if (notificationScreen == "notification") {
            val title = intent.getStringExtra("notification_title") ?: ""
            val body = intent.getStringExtra("notification_body") ?: ""
            navController.navigate("notification_screen/${title}/${body}")
        }
    }

    override fun onBackPressed() {
        if (backClickCount == 1) {
            // If it's the second back click within a certain time, show exit confirmation dialog
            showExitConfirmationDialog()
            resetBackClickCount()
        } else {
            // Increment back click count and schedule reset
            backClickCount++

        }
    }

    private fun resetBackClickCount() {
        backClickCount = 0
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit App")
        builder.setMessage("Are you sure you want to exit?")
        builder.setPositiveButton("Yes") { _, _ ->
            // Exit the app
            finishAffinity()
            super.onBackPressed()
        }
        builder.setNegativeButton("Cancel") { dialogInterface, _ ->
            // Dismiss the dialog (do nothing)
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun hasRequiredPermissions(): Boolean {
        return permissions().all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }


    private fun permissions(): Array<String> {

        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

            )
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val newPermissions = permissions.toMutableList()
            newPermissions.add(
                Manifest.permission.POST_NOTIFICATIONS
            )
            return newPermissions.toList().toTypedArray()
        } else permissions


    }


}

