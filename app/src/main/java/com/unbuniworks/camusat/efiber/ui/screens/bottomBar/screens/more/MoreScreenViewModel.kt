package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Inventory
import androidx.compose.material.icons.outlined.ScreenSearchDesktop
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.model.ApplicationItem

class MoreScreenViewModel():ViewModel() {
    var searchQuery by mutableStateOf("")
        private set


    var originalListOfApplicationItems = listOf(
        ApplicationItem(
            name = "W/O Schedule",
            icon = Icons.Outlined.CalendarMonth
        ),
        ApplicationItem(
            name = "Material Stock",
            icon = Icons.Outlined.Inventory
        ),
        ApplicationItem(
            name = "Audit",
            icon = Icons.Outlined.ScreenSearchDesktop
        ),
        ApplicationItem(
            name = "User Manual",
            icon = Icons.Outlined.Book,
        ),
        ApplicationItem(
            name = "Help",
            icon = Icons.Outlined.HelpOutline
        ),
        ApplicationItem(
            name = "Settings",
            icon = Icons.Outlined.Settings
        )
    )

    var listOfApplicationItems = mutableStateListOf<ApplicationItem>()
        private set

    fun updateSearchQuery(query:String){
        searchQuery = query
        filterApplication()
    }

    private fun filterApplication() {
        listOfApplicationItems.clear()
        listOfApplicationItems.addAll(
            originalListOfApplicationItems.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        )
    }

    init {
        listOfApplicationItems.addAll(
            originalListOfApplicationItems
        )
    }

}