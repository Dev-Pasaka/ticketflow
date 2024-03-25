package com.unbuniworks.camusat.efiber.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.model.BottomBarDestinations

class BottomNavigationViewModel():ViewModel() {

    val bottomBarDestinations  = listOf(
        BottomBarDestinations(title = "Tickets", icon = R.drawable.tickets, route = Screen.Tickets.route),
        BottomBarDestinations(title = "Schedule", icon = R.drawable.schedule, route = Screen.Schedule.route),
        BottomBarDestinations(title = "Material", icon = R.drawable.materials, route = Screen.Material.route),
        BottomBarDestinations(title = "More", icon = R.drawable.more, route = Screen.More.route),
    )
    var selectedDestination by mutableStateOf(bottomBarDestinations.first())
        private set

    fun updateSelectedDestination(bottomBarDestinations: BottomBarDestinations){
        selectedDestination = bottomBarDestinations
    }



}