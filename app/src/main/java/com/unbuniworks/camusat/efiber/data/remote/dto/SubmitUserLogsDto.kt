package com.unbuniworks.camusat.efiber.data.remote.dto

import com.unbuniworks.camusat.efiber.common.Utils
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class SubmitUserLogsDto(
    val device:String = "Mobile",
    val module:String,
    val activity:String,
    val detail:String,
)