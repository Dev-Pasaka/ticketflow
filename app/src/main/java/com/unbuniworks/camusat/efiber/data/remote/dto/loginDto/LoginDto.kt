    package com.unbuniworks.camusat.efiber.data.remote.dto.loginDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.unbuniworks.camusat.efiber.domain.model.User


@Serializable
data class LoginDto(
    @SerialName("access_token")
    val accessToken: String?,
    @SerialName("status")
    val status: Boolean,
    @SerialName("message")
    val message:String,
    @SerialName("passwordset")
    val passwordSet:String = "none",
    @SerialName("user")
    val userDto: UserDto? = null
)
fun LoginDto.toUser():User{
    return User(
        status = status,
        passwordSet = passwordSet,
        email = userDto?.email ?: "",
        firstName = userDto?.firstname ?: "",
        surname = userDto?.surname ?:"",
        mobile = userDto?.mobile ?: ""
    )
}