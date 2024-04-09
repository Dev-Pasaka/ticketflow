package com.unbuniworks.camusat.efiber.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val email:String,
    val password:String,
    val deviceId:String
)