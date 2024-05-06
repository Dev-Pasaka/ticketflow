package com.unbuniworks.camusat.efiber.data.remote.dto.materialDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Material(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("description")
    val description: String,
    @SerialName("dispatchProjectId")
    val dispatchProjectId: String,
    @SerialName("id")
    val id: String,
    @SerialName("materialCode")
    val materialCode: String,
    @SerialName("materialName")
    val materialName: String,
    @SerialName("materialbomId")
    val materialbomId: String?,
    @SerialName("no")
    val no: Int,
    @SerialName("quantityAdded")
    val quantityAdded: String,
    @SerialName("quantityRemaining")
    val quantityRemaining: String,
    @SerialName("quantityUsed")
    val quantityUsed: String,
    @SerialName("status")
    val status: String,
    @SerialName("unit")
    val unit: String,
    @SerialName("updatedAt")
    val updatedAt: String
)