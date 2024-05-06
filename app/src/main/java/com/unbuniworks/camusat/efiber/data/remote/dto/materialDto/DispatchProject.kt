package com.unbuniworks.camusat.efiber.data.remote.dto.materialDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DispatchProject(
    @SerialName("activatedAt")
    val activatedAt: String?,
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
    @SerialName("no")
    val no: Int,
    @SerialName("projectId")
    val projectId: String,
    @SerialName("status")
    val status: String,
    @SerialName("subsidiaryId")
    val subsidiaryId: String,
    @SerialName("type")
    val type: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("userId")
    val userId: String?
)