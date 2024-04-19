package com.unbuniworks.camusat.efiber.data.remote.dto.resetPasswordDto

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordDto(
    val status:Boolean,
    val message:String
)