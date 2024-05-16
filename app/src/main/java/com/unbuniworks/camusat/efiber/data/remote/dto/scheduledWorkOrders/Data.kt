package com.unbuniworks.camusat.efiber.data.remote.dto.scheduledWorkOrders


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("info")
    val info: String,
    @SerialName("infoData")
    val infoData: String,
    @SerialName("kpi")
    val kpi: String,
    @SerialName("link")
    val link: String,
    @SerialName("template")
    val template: String
)