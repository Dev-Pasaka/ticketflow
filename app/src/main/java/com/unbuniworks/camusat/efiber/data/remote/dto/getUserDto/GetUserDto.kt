package com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto


import com.unbuniworks.camusat.efiber.domain.model.UserData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserDto(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String,
    @SerialName("user")
    val user: User
)

fun GetUserDto.toUser():UserData{
    return UserData(
        id = user.id,
        name = "${user.name} ${user.surname}",
        position = user.position.name,
        staffId = user.staffId,
        team = user.team.name,
    )

}