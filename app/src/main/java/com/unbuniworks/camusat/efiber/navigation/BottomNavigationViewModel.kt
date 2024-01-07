package com.unbuniworks.camusat.efiber.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.model.BottomBarDestinations

class BottomNavigationViewModel():ViewModel() {

    val bottomBarDestinations  = listOf(
        BottomBarDestinations(title = "Home", icon = R.drawable.home_icon, route = Screen.Home.route),
        BottomBarDestinations(title = "Projects", icon = R.drawable.project_icon, route = Screen.Projects.route),
        BottomBarDestinations(title = "Tickets", icon = R.drawable.ticket_icon, route = Screen.Tickets.route),
        BottomBarDestinations(title = "Request", icon = R.drawable.baseline_signal_cellular_alt_24, route = Screen.Requests.route),
        BottomBarDestinations(title = "More", icon = R.drawable.more_icon, route = Screen.More.route),
    )
    var selectedDestination by mutableStateOf(bottomBarDestinations.first())
        private set

    fun updateSelectedDestination(bottomBarDestinations: BottomBarDestinations){
        selectedDestination = bottomBarDestinations
    }



}