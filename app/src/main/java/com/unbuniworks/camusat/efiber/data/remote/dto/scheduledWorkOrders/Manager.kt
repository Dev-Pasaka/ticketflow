package com.unbuniworks.camusat.efiber.data.remote.dto.scheduledWorkOrders


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Manager(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)