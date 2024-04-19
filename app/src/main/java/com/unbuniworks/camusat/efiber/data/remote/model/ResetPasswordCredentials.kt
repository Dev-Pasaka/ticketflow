package com.unbuniworks.camusat.efiber.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordCredentials(
    val email:String,
    val deviceId:String = "",
    val deviceDescription:String = ""
)
