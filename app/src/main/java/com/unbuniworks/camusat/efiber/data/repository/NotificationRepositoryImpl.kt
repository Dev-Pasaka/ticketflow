package com.unbuniworks.camusat.efiber.data.repository

import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.remote.dto.notificationsDto.NotificationDto
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.domain.repository.NotificationRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders.Authorization
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotificationRepositoryImpl(
    private val httpClient:HttpClient = HttpClient
):NotificationRepository {
    override suspend fun sendFCMToken(token: Map<String, String>, authToken: String): Boolean = withContext(Dispatchers.IO) {
        httpClient.client.post("${Constants.baseUrl}auth/notifications/token"){
            header(Authorization, "Bearer $authToken")
            setBody(token)
        }.bodyAsText().toBoolean()
    }

    override suspend fun getAllNotifications(token:String): NotificationDto = withContext(Dispatchers.IO) {
        httpClient.client.get("${Constants.baseUrl}notifications/user/unread"){
            header(Authorization, "Bearer $token")
        }.body<NotificationDto>()
    }

    override suspend fun clearAllNotifications(token:String): NotificationDto  = withContext(Dispatchers.IO) {
        httpClient.client.get("${Constants.baseUrl}notifications/user/clear"){
            header(Authorization, "Bearer $token")
        }.body<NotificationDto>()
    }

    override suspend fun clearNotification(token:String,notificationId:String): NotificationDto  = withContext(Dispatchers.IO) {
        httpClient.client.get("${Constants.baseUrl}notifications/notification/clear/$notificationId"){
            header(Authorization, "Bearer $token")
        }.body<NotificationDto>()
    }
}

suspend fun main(){
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJmYmYwZmY1Yi1kNzI4LTQwZmItOWI0Yy00YzYzZWYwZDkzZmMiLCJlbWFpbCI6ImRldi5wYXNha2FAZ21haWwuY29tIiwiaWF0IjoxNzE3NjA2MjI1LCJleHAiOjE3MTc2MDkyMjV9.la0JownMoMKmN0tmCLb4-kp1_lgOJWJOi7jMX43KDM0"
    val notificationRepository = NotificationRepositoryImpl().sendFCMToken(authToken = token, token = mapOf("Test" to "test1234"))
    println(notificationRepository)

}