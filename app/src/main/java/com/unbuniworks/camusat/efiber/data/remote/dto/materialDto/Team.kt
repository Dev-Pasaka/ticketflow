package com.unbuniworks.camusat.efiber.data.remote.dto.materialDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("firstTechnicianId")
    val firstTechnicianId: String,
    @SerialName("id")
    val id: String,
    @SerialName("leadTechnicianId")
    val leadTechnicianId: String,
    @SerialName("name")
    val name: String,
    @SerialName("otherTechnicianId")
    val otherTechnicianId: String?,
    @SerialName("secondTechnicianId")
    val secondTechnicianId: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("updatedAt")
    val updatedAt: String
)