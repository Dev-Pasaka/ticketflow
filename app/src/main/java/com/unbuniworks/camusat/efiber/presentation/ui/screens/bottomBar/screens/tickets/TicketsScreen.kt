package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.BottomNavigationViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.BottomAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.components.TicketsScreenLowerSection
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TicketsScreen(
    bottomNavigationViewModel: BottomNavigationViewModel,
    ticketsScreenViewModel: TicketsScreenViewModel,
    navController: NavHostController,
) {

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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            TicketsScreenLowerSection(
                ticketsScreenViewModel = ticketsScreenViewModel,
                navController = navController
            )
            
        }
    }
}