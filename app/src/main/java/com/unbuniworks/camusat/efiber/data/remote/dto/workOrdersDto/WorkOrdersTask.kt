package com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkOrdersTask(
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("statusColor")
    val statusColor:String?,
    @SerialName("technicianId")
    val technicianId: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("workOrderId")
    val workOrderId: String,
)