package com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto

import kotlinx.serialization.Serializable

@Serializable
data class TicketDetail(
    val key: String,
    val value: String
)
