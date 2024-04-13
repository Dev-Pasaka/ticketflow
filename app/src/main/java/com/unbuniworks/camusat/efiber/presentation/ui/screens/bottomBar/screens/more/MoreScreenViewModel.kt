package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more

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
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more.model.ApplicationItem

class MoreScreenViewModel():ViewModel() {
    var searchQuery by mutableStateOf("")
        private set


    var originalListOfApplicationItems = listOf(
        ApplicationItem(
            name = "User Manual",
            icon = R.drawable.manual
        ),

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