package com.unbuniworks.camusat.efiber.domain.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.notificationsDto.NotificationDto

interface NotificationRepository {
    suspend fun sendFCMToken(token: Map<String, String>, authToken: String):Boolean
    suspend fun getAllNotifications(token:String): NotificationDto
    suspend fun clearAllNotifications(token:String):NotificationDto
    suspend fun clearNotification(token:String, notificationId:String):NotificationDto
}