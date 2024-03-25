package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.BottomNavigationViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.BottomAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components.ScheduleItemBody
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components.ScheduleScreenUpperSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Schedule(
    bottomNavigationViewModel: BottomNavigationViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    navController: NavHostController
){

    Scaffold(
        bottomBar = {
            BottomAppBar(
                bottomNavigationViewModel = bottomNavigationViewModel,
                navController = navController
            )
        },
        topBar = {
            TopAppBar(navController = navController)

        },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.background)

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScheduleScreenUpperSection(scheduleScreenViewModel = scheduleScreenViewModel)
            ScheduleItemBody(scheduleScreenViewModel = scheduleScreenViewModel)
        }
    }
}