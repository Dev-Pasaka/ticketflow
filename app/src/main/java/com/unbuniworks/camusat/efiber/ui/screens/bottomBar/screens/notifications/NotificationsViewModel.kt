package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NotificationsViewModel():ViewModel() {
    var notificationCount by mutableIntStateOf(4)
        private set

    val notifications by mutableStateOf(
        listOf(
            "QA Approval for Ticket #62379923",
            "QA Approval for Ticket #98479923",
            "New Materials Received",
            "QA Rejected for Ticket #78239121",
        )
    )
}