package com.unbuniworks.camusat.efiber.data.remote.dto.templatesDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemplatesDtoItem(
    @SerialName("button")
    val button: Button,
    @SerialName("features")
    val features: List<Feature>,
    @SerialName("templateName")
    val templateName: String
)