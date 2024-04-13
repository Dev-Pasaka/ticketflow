package com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkOrderTask(
    @SerialName("buttonName")
    val buttonName: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("features")
    val features: List<Feature>,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("technicianId")
    val technicianId: String?,
    @SerialName("templateComponentId")
    val templateComponentId: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("workOrderId")
    val workOrderId: String,
    val isSpecialFeature:Boolean,
    val featureName:String?,

)