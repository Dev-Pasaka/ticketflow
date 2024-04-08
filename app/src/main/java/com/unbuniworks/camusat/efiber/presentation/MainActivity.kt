package com.unbuniworks.camusat.efiber.presentation

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.res.colorResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.NavGraph
import com.unbuniworks.camusat.efiber.presentation.ui.theme.CamusatTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private var backClickCount = 0

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {

        if (!hasRequiredPermissions()){
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )

        }

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

    override fun onBackPressed() {
        super.onBackPressed()
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

    private fun hasRequiredPermissions():Boolean{
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }


    companion object{
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )


    }


}

