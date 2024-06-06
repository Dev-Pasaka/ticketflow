package com.unbuniworks.camusat.efiber.data.remote.dto.notificationsDto


import com.unbuniworks.camusat.efiber.domain.model.Notification
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    @SerialName("data")
    val `data`: List<Data> = emptyList(),
    @SerialName("status")
    val status: Boolean
){
     fun toNotificationList(): List<Notification> {
         return data.map {
             Notification(
                 id = it.id,
                 title = it.title,
                 description = it.description,
                 status = it.status,
                 createdAt = it.createdAt
             )
         }
     }
}