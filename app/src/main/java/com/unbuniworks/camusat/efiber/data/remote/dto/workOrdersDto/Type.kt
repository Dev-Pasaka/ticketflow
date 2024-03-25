package com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("name")
    val name: String
)