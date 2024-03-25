package com.unbuniworks.camusat.efiber.data.remote.dto.templatesDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Button(
    @SerialName("name")
    val name: String
)