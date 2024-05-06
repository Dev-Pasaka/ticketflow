package com.unbuniworks.camusat.efiber.data.remote.dto.materialDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("dispatchProject")
    val dispatchProject: List<DispatchProject>,
    @SerialName("id")
    val id: String,
    @SerialName("material")
    val material: Material,
    @SerialName("materialId")
    val materialId: String,
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
    @SerialName("team")
    val team: Team,
    @SerialName("teamId")
    val teamId: String,
    @SerialName("type")
    val type: String,
    @SerialName("updatedAt")
    val updatedAt: String
)