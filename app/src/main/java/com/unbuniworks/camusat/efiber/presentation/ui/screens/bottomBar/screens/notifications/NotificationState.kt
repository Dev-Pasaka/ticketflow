package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications

import com.unbuniworks.camusat.efiber.domain.model.Notification

data class NotificationState(
    val isLoading: Boolean = false,
    val status: Boolean = false,
    val data: List<Notification> = emptyList(),
    val message: String = ""
)
