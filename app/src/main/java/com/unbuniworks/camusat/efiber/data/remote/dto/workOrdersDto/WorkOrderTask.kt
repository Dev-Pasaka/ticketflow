package com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkOrderTask(
    @SerialName("completedAt")
    val completedAt: String?,
    @SerialName("completedEndAt")
    val completedEndAt: String?,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("data")
    val `data`: List<DataX>,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("scheduledAt")
    val scheduledAt: String?,
    @SerialName("scheduledEndAt")
    val scheduledEndAt: String?,
    @SerialName("status")
    val status: String,
    @SerialName("technicianId")
    val technicianId: String,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("workOrderId")
    val workOrderId: String
)