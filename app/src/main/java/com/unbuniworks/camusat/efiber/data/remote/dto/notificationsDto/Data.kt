package com.unbuniworks.camusat.efiber.data.remote.dto.notificationsDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: String,
    @SerialName("no")
    val no: Int,
    @SerialName("status")
    val status: String,
    @SerialName("title")
    val title: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("userId")
    val userId: String
)