package com.unbuniworks.camusat.efiber

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.unbuniworks.camusat.efiber.navigation.NavGraph
import com.unbuniworks.camusat.efiber.ui.screens.auth.LoginsScreen
import com.unbuniworks.camusat.efiber.ui.theme.CamusatTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private var backClickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
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
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    CamusatTheme {
        Greeting("Android")
    }
}