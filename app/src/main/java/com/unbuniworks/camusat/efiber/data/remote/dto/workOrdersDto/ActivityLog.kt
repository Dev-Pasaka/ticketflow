package com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityLog(
    @SerialName("activity")
    val activity: String,
    @SerialName("comment")
    val comment: String?,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("reason")
    val reason: String?,
    @SerialName("statusUpdate")
    val statusUpdate: String,
    @SerialName("team")
    val team: TeamX?,
    @SerialName("type")
    val type: String,
    @SerialName("User")
    val user: User?
)