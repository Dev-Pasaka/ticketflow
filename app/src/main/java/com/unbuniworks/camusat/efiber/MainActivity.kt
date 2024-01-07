package com.unbuniworks.camusat.efiber

import android.os.Build
import android.os.Bundle
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