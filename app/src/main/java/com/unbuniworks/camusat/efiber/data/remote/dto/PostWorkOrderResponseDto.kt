package com.unbuniworks.camusat.efiber.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostWorkOrderResponseDto(
    val status:Boolean,
    val message:String
)