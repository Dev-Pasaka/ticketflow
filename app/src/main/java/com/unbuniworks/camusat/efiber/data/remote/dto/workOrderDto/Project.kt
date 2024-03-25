package com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: Type
)