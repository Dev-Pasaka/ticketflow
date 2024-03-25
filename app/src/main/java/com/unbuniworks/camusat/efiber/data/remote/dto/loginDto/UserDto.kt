package com.unbuniworks.camusat.efiber.data.remote.dto.loginDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("email")
    val email: String,
    @SerialName("firstname")
    val firstname: String,
    @SerialName("id")
    val id: String,
    @SerialName("mobile")
    val mobile: String,
    @SerialName("surname")
    val surname: String
)