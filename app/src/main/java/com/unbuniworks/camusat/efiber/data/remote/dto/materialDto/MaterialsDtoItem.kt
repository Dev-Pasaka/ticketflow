package com.unbuniworks.camusat.efiber.data.remote.dto.materialDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialsDtoItem(
    @SerialName("client")
    val client: Client,
    @SerialName("clientId")
    val clientId: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("id")
    val id: String,
    @SerialName("material")
    val material: Material,
    @SerialName("materialId")
    val materialId: String,
    @SerialName("quantityAdded")
    val quantityAdded: String,
    @SerialName("quantityRemaining")
    val quantityRemaining: String,
    @SerialName("quantityUsed")
    val quantityUsed: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("updatedAt")
    val updatedAt: String
)

fun MaterialsDtoItem.toMaterial():com.unbuniworks.camusat.efiber.domain.model.Material{
    return com.unbuniworks.camusat.efiber.domain.model.Material(
        name  = material.materialName,
        qty = material.quantityRemaining
    )
}