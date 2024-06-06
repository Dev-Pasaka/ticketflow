package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications

import android.app.Activity

sealed class NotificationEvents() {
    data class ClearAllNotifications(val activity: Activity) : NotificationEvents()
    data class ClearNotification(val activity: Activity, val notificationId:String) : NotificationEvents()
    data class GetAllNotifications(val activity: Activity) : NotificationEvents()

}