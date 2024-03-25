package com.unbuniworks.camusat.efiber.data.remote.dto.templatesDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    @SerialName("classification")
    val classification: String,
    @SerialName("inputType")
    val inputType: String,
    @SerialName("name")
    val name: String,
    @SerialName("options")
    val options: List<String>?
)