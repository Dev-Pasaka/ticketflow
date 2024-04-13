package com.unbuniworks.camusat.efiber.domain.model

data class UserData(
    val id:String = "",
    val name:String = "",
    val position:String = "",
    val staffId: String? = null,
    val buildNumber:String = "",
    val team:String = ""
)