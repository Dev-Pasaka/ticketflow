package com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("email")
    val email: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("position")
    val position: Position,
    @SerialName("staffId")
    val staffId: String?,
    @SerialName("surname")
    val surname: String,
    @SerialName("team")
    val team: Team
)