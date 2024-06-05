package com.unbuniworks.camusat.efiber.presentation.ui.screens.splashScreen

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.services.foregroundServices.NotificationService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity

    val splashScreenViewModel = viewModel<SplashScreenViewModel>()


    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        val notificationScreen = activity.intent.getStringExtra("notification_screen")
        if (notificationScreen == "notification") {
            val title = activity.intent.getStringExtra("notification_title") ?: ""
            val body = activity.intent.getStringExtra("notification_body") ?: ""
            navController.navigate("notification_screen/${title}/${body}")
        }
        else if (splashScreenViewModel.isTokenValid(activity = activity)){

            navController.navigate(Screen.SelectModule.route){
                navController.popBackStack()
            }
        }else{
            navController.navigate("auth"){
                Intent(activity, NotificationService::class.java).also {
                    it.action = NotificationService.Actions.START.toString()
                }
                navController.popBackStack()
            }
        }

    }

    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.camusat_logo_product_removebg_preview),
                contentDescription = "Logo product",
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .alpha(alphaAnim.value)
            )
        }
    }



}

fun handleNotificationIntent(intent: Intent, navController: NavController) {
    val notificationScreen = intent.getStringExtra("notification_screen")
    if (notificationScreen == "notification") {
        val title = intent.getStringExtra("notification_title") ?: ""
        val body = intent.getStringExtra("notification_body") ?: ""
        navController.navigate("notification_screen/${title}/${body}")
    }
}