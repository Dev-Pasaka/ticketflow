package com.unbuniworks.camusat.efiber.domain.model

import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.Position
import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.Team
import kotlinx.serialization.SerialName

data class UserData(
    val id:String = "",
    val name:String = "",
    val position:String = "",
    val staffId: String? = null,
    val buildNumber:String = "",
    val team:String = ""
)