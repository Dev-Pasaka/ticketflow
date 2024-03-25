package com.unbuniworks.camusat.efiber.data.remote.dto.materialDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Client(
    @SerialName("countryId")
    val countryId: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("id")
    val id: String,
    @SerialName("logo")
    val logo: String?,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("updatedAt")
    val updatedAt: String
)