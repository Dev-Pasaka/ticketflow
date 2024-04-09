package com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)