package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.components.NotificationsBodySections
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.components.NotificationsScreenUpperSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationsScreen(navController: NavHostController, notificationsViewModel: NotificationsViewModel) {

    Scaffold(
        bottomBar = {
        },
        topBar = {
            TopAppBar(navController = navController)

        },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.background)
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            ) {
            NotificationsScreenUpperSection(navController = navController, notificationsViewModel)
            NotificationsBodySections(notificationsViewModel = notificationsViewModel)
        }
    }
}