package com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataX(
    @SerialName("description")
    val description: String,
    @SerialName("value")
    val value: String
)