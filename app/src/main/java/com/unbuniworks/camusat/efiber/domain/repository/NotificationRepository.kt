package com.unbuniworks.camusat.efiber.domain.repository

interface NotificationRepository {
    suspend fun sendFCMToken(token: Map<String, String>, authToken: String):Boolean
    suspend fun getNotification():Unit
    suspend fun clearNotification():Unit
}