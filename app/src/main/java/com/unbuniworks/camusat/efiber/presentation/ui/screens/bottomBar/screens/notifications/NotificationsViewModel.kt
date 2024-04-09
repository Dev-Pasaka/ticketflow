package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NotificationsViewModel():ViewModel() {
    var notificationCount by mutableIntStateOf(4)
        private set

    val notifications by mutableStateOf(
        emptyList<String>()
    )
}