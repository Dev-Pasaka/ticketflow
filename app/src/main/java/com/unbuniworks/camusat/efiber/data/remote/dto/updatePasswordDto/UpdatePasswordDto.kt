package com.unbuniworks.camusat.efiber.data.remote.dto.updatePasswordDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePasswordDto(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean
)